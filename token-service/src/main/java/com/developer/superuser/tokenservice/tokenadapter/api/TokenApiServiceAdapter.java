package com.developer.superuser.tokenservice.tokenadapter.api;

import com.developer.superuser.tokenservice.core.enumeration.TokenType;
import com.developer.superuser.tokenservice.token.Token;
import com.developer.superuser.tokenservice.token.TokenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Slf4j
public class TokenApiServiceAdapter implements TokenApiService {
    private final TokenApi tokenApi;

    @Override
    public Token fetchTokenB2b(Token token) {
        Token tokenResponse = tokenApi.fetchB2b(token);
        log.info("Printing token response from doku api --- {}", tokenResponse);
        return tokenResponse;
    }

    @Override
    public Token fetchTokenB2b2c(Token token) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}