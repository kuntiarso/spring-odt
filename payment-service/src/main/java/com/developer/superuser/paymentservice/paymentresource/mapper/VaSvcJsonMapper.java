package com.developer.superuser.paymentservice.paymentresource.mapper;

import com.developer.superuser.paymentservice.core.dto.AdditionalDto;
import com.developer.superuser.paymentservice.core.dto.AmountDto;
import com.developer.superuser.paymentservice.core.property.DokuConfigProperties;
import com.developer.superuser.paymentservice.payment.Payment;
import com.developer.superuser.paymentservice.paymentresource.dto.PaymentVaRequest;
import com.developer.superuser.paymentservice.paymentresource.dto.PaymentVaResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class VaSvcJsonMapper {
    private final ObjectMapper mapper;
    private final DokuConfigProperties dokuConfig;

    public JsonNode toVaJsonRequest(PaymentVaRequest request, Payment payment, JsonNode tokenResponse) {
        ObjectNode header = mapper.createObjectNode()
                .put("timestamp", Instant.now().toString())
                .put("clientId", dokuConfig.getMerchant().getClientId())
                .put("requestId", payment.getRequestId())
                .put("token", tokenResponse.path("accessToken").asText(null));
        ObjectNode body = mapper.createObjectNode()
                .put("paymentId", payment.getId())
                .put("partnerId", request.getPartnerId())
                .put("customerNo", request.getCustomerNo())
                .put("vaNo", request.getVaNo())
                .put("vaName", request.getVaName())
                .put("transactionType", request.getTransactionType());
        ObjectNode billedAmount = mapper.createObjectNode()
                .put("value", payment.getAmount().getValue())
                .put("currency", payment.getAmount().getCurrency().name());
        ObjectNode additional = mapper.createObjectNode()
                .put("channel", request.getChannel());
        body.set("billedAmount", billedAmount);
        body.set("additional", additional);
        return body;
    }

    public PaymentVaResponse fromVaJsonResponse(JsonNode response) {
        AmountDto amount = response.path("billedAmount").isNull() ? null : AmountDto.builder()
                .setValue(response.path("billedAmount").path("value").decimalValue())
                .setCurrency(response.path("billedAmount").path("currency").asText(null))
                .build();
        AdditionalDto additional = response.path("additional").isNull() ? null : AdditionalDto.builder()
                .setChannel(response.path("additional").path("channel").asText(null))
                .setHowToPayPage(response.path("additional").path("howToPayPage").asText(null))
                .setHowToPayApi(response.path("additional").path("howToPayApi").asText(null))
                .build();
        return PaymentVaResponse.builder()
                .setId(response.path("paymentId").asLong())
                .setVaNo(response.path("vaNo").asText(null))
                .setAmount(amount)
                .setAdditionalInfo(additional)
                .setExpiredDate(response.path("expiredDate").isNull() ? null : Instant.parse(response.path("expiredDate").asText("")))
                .build();
    }
}