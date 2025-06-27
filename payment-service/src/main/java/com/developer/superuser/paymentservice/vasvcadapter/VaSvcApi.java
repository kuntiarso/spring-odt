package com.developer.superuser.paymentservice.vasvcadapter;

import com.developer.superuser.paymentservice.core.property.VaSvcConfigProperties;
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
public class VaSvcApi {
    private final RestClient vaSvcRestClient;
    private final VaSvcConfigProperties vaSvcConfig;

    public ResponseData<JsonNode> createVa(JsonNode request) {
        log.info("Request body for create va --- {}", request.toString());
        return vaSvcRestClient.post()
                .uri(vaSvcConfig.getEndpoint().get("va-create"))
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}