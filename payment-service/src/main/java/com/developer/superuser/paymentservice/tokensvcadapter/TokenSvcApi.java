package com.developer.superuser.paymentservice.tokensvcadapter;

import com.developer.superuser.paymentservice.core.property.TokenSvcConfigProperties;
import com.developer.superuser.shared.data.ResponseData;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenSvcApi {
    private final RestClient tokenSvcRestClient;
    private final TokenSvcConfigProperties tokenSvcConfig;

    public ResponseData<JsonNode> getSign(JsonNode request) {
        log.info("Request body for generate signature --- {}", request.toString());
        return tokenSvcRestClient.post()
                .uri(tokenSvcConfig.getEndpoint().get("get-sign"))
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public ResponseData<JsonNode> getToken(JsonNode request) {
        log.info("Request body for get token --- {}", request.toString());
        return tokenSvcRestClient.post()
                .uri(tokenSvcConfig.getEndpoint().get("get-token"))
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}