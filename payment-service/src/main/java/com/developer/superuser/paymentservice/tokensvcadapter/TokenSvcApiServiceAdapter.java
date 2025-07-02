package com.developer.superuser.paymentservice.tokensvcadapter;

import com.developer.superuser.paymentservice.core.helper.OptionalOrElseThrow;
import com.developer.superuser.paymentservice.tokensvc.TokenSvcApiService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TokenSvcApiServiceAdapter implements TokenSvcApiService {
    private final TokenSvcApi tokenSvcApi;
    private final OptionalOrElseThrow<JsonNode> optionalOrElseThrow;

    @Override
    public JsonNode getSign(JsonNode request) {
        log.info("Calling token-service api for getting signature");
        return optionalOrElseThrow.execute(tokenSvcApi.getSign(request)).getBody();
    }

    @Override
    public JsonNode getToken(JsonNode request) {
        log.info("Calling token-service api for getting token");
        return optionalOrElseThrow.execute(tokenSvcApi.getToken(request)).getBody();
    }
}