package com.developer.superuser.virtualaccountservice.vapayment;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DefaultVaPersistenceServiceAdapter implements VaPersistenceService {
    @Override
    public void saveVa(VaPaymentDetail vaPaymentDetail) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}