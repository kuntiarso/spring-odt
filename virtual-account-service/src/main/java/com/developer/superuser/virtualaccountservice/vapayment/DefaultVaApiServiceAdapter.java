package com.developer.superuser.virtualaccountservice.vapayment;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DefaultVaApiServiceAdapter implements VaApiService {
    @Override
    public VaPaymentDetail createVa(VaPaymentDetail vaPaymentDetail) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}