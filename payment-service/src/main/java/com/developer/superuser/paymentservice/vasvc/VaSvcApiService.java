package com.developer.superuser.paymentservice.vasvc;

import com.fasterxml.jackson.databind.JsonNode;

public interface VaSvcApiService {
    JsonNode createVa(JsonNode request);
}