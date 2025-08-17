package com.developer.superuser.virtualaccountservice.tokensvc;

import com.fasterxml.jackson.databind.JsonNode;

public interface TokenSvcApiService {
    JsonNode getSign(JsonNode request);
}