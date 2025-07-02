package com.developer.superuser.paymentservice.paymentresource.mapper;

import com.developer.superuser.paymentservice.PaymentServiceConstant;
import com.developer.superuser.paymentservice.core.property.DokuConfigProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenSvcJsonMapper {
    private final ObjectMapper mapper;
    private final DokuConfigProperties dokuConfig;

    public JsonNode toSignJsonRequest(String signType) {
        if (PaymentServiceConstant.SIGN_TYPE_BASIC.equalsIgnoreCase(signType)) {
            return mapper.createObjectNode()
                    .put("apiType", PaymentServiceConstant.SIGN_TYPE_BASIC)
                    .put("clientId", dokuConfig.getMerchant().getClientId());
        }
        return mapper.createObjectNode();
    }

    public JsonNode toTokenJsonRequest(JsonNode signJsonResponse) {
        return mapper.createObjectNode()
                .put("dokuTokenType", "B2B")
                .put("clientId", dokuConfig.getMerchant().getClientId())
                .put("signature", signJsonResponse.path("signature").asText(""))
                .put("grantType", "CLIENT_CREDENTIALS")
                .put("timestamp", signJsonResponse.path("timestamp").asText(""));
    }
}