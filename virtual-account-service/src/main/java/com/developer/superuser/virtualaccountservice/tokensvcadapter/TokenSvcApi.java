package com.developer.superuser.virtualaccountservice.tokensvcadapter;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.virtualaccountservice.core.property.TokenSvcConfigProperties;
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
}