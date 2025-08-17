package com.developer.superuser.virtualaccountservice.tokensvcadapter;

import com.developer.superuser.virtualaccountservice.core.helper.OptionalOrElseThrow;
import com.developer.superuser.virtualaccountservice.tokensvc.TokenSvcApiService;
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
        log.debug("Calling token-svc api for generating symmetric sign");
        return optionalOrElseThrow.execute(tokenSvcApi.getSign(request)).getBody();
    }
}