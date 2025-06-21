package com.developer.superuser.virtualaccountservice.vapayment;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface VaApiService {
    VaPaymentDetail createVa(VaPaymentDetail vaPaymentDetail) throws JsonProcessingException;
}