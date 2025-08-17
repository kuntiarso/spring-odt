package com.developer.superuser.virtualaccountservice.vapayment;

import com.fasterxml.jackson.databind.JsonNode;

public interface VaApiService {
    VaPaymentDetail createVa(VaPaymentDetail vaPaymentDetail, JsonNode signature);
}