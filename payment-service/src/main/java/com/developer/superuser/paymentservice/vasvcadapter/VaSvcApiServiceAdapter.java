package com.developer.superuser.paymentservice.vasvcadapter;

import com.developer.superuser.paymentservice.core.helper.OptionalOrElseThrow;
import com.developer.superuser.paymentservice.vasvc.VaSvcApiService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class VaSvcApiServiceAdapter implements VaSvcApiService {
    private final VaSvcApi vaSvcApi;
    private final OptionalOrElseThrow<JsonNode> optionalOrElseThrow;

    @Override
    public JsonNode createVa(JsonNode request) {
        log.info("Calling va-service api for creating va");
        return optionalOrElseThrow.execute(vaSvcApi.createVa(request)).getBody();
    }
}