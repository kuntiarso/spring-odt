package com.developer.superuser.tokenservice.tokenresource;

import com.developer.superuser.tokenservice.core.enumeration.TokenType;
import com.developer.superuser.tokenservice.core.helper.GenericHelper;
import com.developer.superuser.tokenservice.token.Token;
import com.developer.superuser.tokenservice.token.TokenCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenHandler {
    private final TokenCacheService tokenCacheService;
    private final GenericHelper<TokenRequestDto, Void> getAccessTokenValidator;
    private final TokenCoreMapper tokenCoreMapper;

    public ResponseEntity<TokenResponseDto> getAccessToken(TokenRequestDto request) {
        try {
            Token token;
            log.info("Checking incoming arguments for getAccessToken");
            getAccessTokenValidator.execute(request);
            log.info("Differentiating get token process by dokuTokenType");
            if (TokenType.B2B.equals(request.getDokuTokenType())) {
                log.info("Coming to get token b2b process");
                token = getTokenB2b(request);
            } else if (TokenType.B2B2C.equals(request.getDokuTokenType())) {
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
            }else {
                log.info("Unknown doku token type --- {}", request.getDokuTokenType());
                throw new UnsupportedOperationException("Unsupported doku token type");
            }
            return ResponseEntity.ok(tokenCoreMapper.mapResponse(token));
        } catch (Exception ex) {
            log.error("Error has occurred in getTokenB2b handler --- {}", ex.getMessage(), ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        }
    }

    private Token getTokenB2b(TokenRequestDto request) {
        log.info("Getting token b2b from cache or retrieving from api");
        Token responseToken = tokenCacheService.getOrFetchTokenB2b(tokenCoreMapper.map(request));
        if (tokenCacheService.isTokenB2bValid(responseToken)) {
            log.info("Token b2b is retrieved successfully");
            return responseToken;
        }
        throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Invalid or missing token b2b");
    }
}