package com.developer.superuser.tokenservice.token;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DefaultTokenApiServiceAdapter implements TokenApiService {
    @Override
    public Token fetchTokenB2b(Token token) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public Token fetchTokenB2b2c(Token token) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}