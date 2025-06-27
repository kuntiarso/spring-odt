package com.developer.superuser.paymentservice.tokensvcadapter;

import com.developer.superuser.paymentservice.core.helper.OptionalOrElseThrow;
import com.developer.superuser.paymentservice.tokensvc.TokenSvcApiService;
import com.developer.superuser.shared.data.ResponseData;
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
        ResponseData<JsonNode> responseData = optionalOrElseThrow.execute(tokenSvcApi.getSign(request));
        log.info("Response from token-service api for getting signature --- {}", responseData.toString());
        return responseData.getBody();
    }

    @Override
    public JsonNode getToken(JsonNode request) {
        log.info("Calling token-service api for getting token");
        ResponseData<JsonNode> responseData = optionalOrElseThrow.execute(tokenSvcApi.getToken(request));
        log.info("Response from token-service api for getting token --- {}", responseData.toString());
        return responseData.getBody();
    }
}