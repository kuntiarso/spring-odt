package com.developer.superuser.tokenservice.tokenresource;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.tokenservice.core.data.ErrorData;
import com.developer.superuser.tokenservice.core.enumeration.TokenType;
import com.developer.superuser.tokenservice.core.helper.AccessTokenHelper;
import com.developer.superuser.tokenservice.token.Token;
import com.developer.superuser.tokenservice.token.TokenCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenHandler {
    private final TokenCacheService tokenCacheService;
    private final AccessTokenHelper accessTokenHelper;
    private final TokenCoreMapper tokenCoreMapper;

    public ResponseData<?> getToken(TokenRequestDto request) {
        ErrorData error = null;
        try {
            Token token = null;
            log.info("Getting token based on tokenType");
            if (TokenType.B2B.equals(request.getTokenType())) {
                log.info("B2b token type");
                token = getTokenB2b(request);
            } else if (TokenType.B2B2C.equals(request.getTokenType())) {
                log.info("B2b2c token type");
                error = ErrorData.error(HttpStatus.NOT_IMPLEMENTED);
            } else {
                log.info("Unknown token type --- {}", request.getTokenType());
                error = ErrorData.error(HttpStatus.BAD_REQUEST, "Unknown token type");
            }
            if (error != null) {
                return ResponseData.error(error);
            } else if (!tokenCacheService.isTokenValid(token)) {
                return ResponseData.error(token.getError());
            }
            return ResponseData.success(tokenCoreMapper.mapResponse(token));
        } catch (Exception ex) {
            log.error("Unknown error has occurred while getting token", ex);
            return ResponseData.error(ErrorData.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage()));
        }
    }

    private Token getTokenB2b(TokenRequestDto request) {
        accessTokenHelper.validate(request);
        Token token = tokenCacheService.getOrFetchTokenB2b(tokenCoreMapper.map(request));
        log.info("Printing token from cacheable --- {}", token);
        return token;
    }
}