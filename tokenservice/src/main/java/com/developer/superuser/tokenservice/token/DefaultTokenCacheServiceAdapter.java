package com.developer.superuser.tokenservice.token;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DefaultTokenCacheServiceAdapter implements TokenCacheService {
    @Override
    public Token getOrFetchTokenB2b(Token token) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void evictTokenB2b(String clientId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public boolean isTokenB2bValid(Token token) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}