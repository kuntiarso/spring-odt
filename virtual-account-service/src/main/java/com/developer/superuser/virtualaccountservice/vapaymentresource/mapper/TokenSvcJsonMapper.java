package com.developer.superuser.virtualaccountservice.vapaymentresource.mapper;

import com.developer.superuser.virtualaccountservice.VirtualAccountServiceConstant;
import com.developer.superuser.virtualaccountservice.core.helper.DigestHelper;
import com.developer.superuser.virtualaccountservice.core.property.DokuConfigProperties;
import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenSvcJsonMapper {
    private final ObjectMapper mapper;
    private final DokuConfigProperties dokuConfig;
    private final DigestHelper digestHelper;

    @SneakyThrows
    public JsonNode toSignJsonRequest(VaPaymentDetail va) {
        String minifiedPayload = mapper.writeValueAsString(va);
        log.info("Printing minified payload for digest --- {}", minifiedPayload);
        return mapper.createObjectNode()
                .put("signType", VirtualAccountServiceConstant.VALUE_SIGN_SNAP)
                .put("algoType", VirtualAccountServiceConstant.VALUE_ALGO_SYMMETRIC)
                .put("clientId", va.getHeader().getClientId())
                .put("requestId", va.getHeader().getRequestId())
                .put("httpMethod", HttpMethod.POST.name())
                .put("targetEndpoint", dokuConfig.getApi().getEndpoint().get("va-create"))
                .put("token", va.getHeader().getToken())
                .put("digest", digestHelper.generate(minifiedPayload));
    }
}