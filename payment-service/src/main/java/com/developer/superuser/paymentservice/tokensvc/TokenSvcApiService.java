package com.developer.superuser.paymentservice.tokensvc;

import com.fasterxml.jackson.databind.JsonNode;

public interface TokenSvcApiService {
    JsonNode getSign(JsonNode request);
    JsonNode getToken(JsonNode request);
}