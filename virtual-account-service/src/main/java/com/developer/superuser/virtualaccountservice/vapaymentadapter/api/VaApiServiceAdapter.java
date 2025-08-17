package com.developer.superuser.virtualaccountservice.vapaymentadapter.api;

import com.developer.superuser.virtualaccountservice.vapayment.VaApiService;
import com.developer.superuser.virtualaccountservice.vapayment.VaPaymentDetail;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class VaApiServiceAdapter implements VaApiService {
    private final VaApi vaApi;

    @Override
    public VaPaymentDetail createVa(VaPaymentDetail va, JsonNode signature) {
        va.getHeader().setSignature(signature.path("signature").asText(null));
        va.getHeader().setTimestamp(signature.path("timestamp").asText(null));
        return vaApi.dokuCreateVa(va);
    }
}