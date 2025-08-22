package com.developer.superuser.tokenservice.tokenresource;

import com.developer.superuser.shared.data.ResponseData;
import com.developer.superuser.shared.openapi.contract.ErrorData;
import com.developer.superuser.shared.openapi.contract.TokenRequest;
import com.developer.superuser.shared.openapi.contract.TokenType;
import com.developer.superuser.shared.utility.Errors;
import com.developer.superuser.tokenservice.core.helper.TokenB2bHelper;
import com.developer.superuser.tokenservice.token.Token;
import com.developer.superuser.tokenservice.token.TokenCacheService;
import com.developer.superuser.tokenservice.tokenresource.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenHandler {
    private final TokenCacheService tokenCacheService;
    private final TokenB2bHelper tokenB2bHelper;
    private final TokenMapper tokenMapper;

    public ResponseData<?> getToken(TokenRequest request) {
        ErrorData error = null;
        try {
            Token token = Token.builder().build();
            log.debug("Getting token based on tokenType");
            if (TokenType.B2_B.equals(request.getTokenType())) {
                log.info("Getting b2b token");
                tokenB2bHelper.validate(request);
                token = tokenB2bHelper.execute(tokenMapper.mapCore(request));
            } else if (TokenType.B2_B2_C.equals(request.getTokenType())) {
                log.info("Getting b2b2c token");
                error = Errors.error(HttpStatus.NOT_IMPLEMENTED.value());
            } else {
                log.error("Unknown token type --- {}", request.getTokenType());
                error = Errors.badRequest("Unknown token type");
            }
            if (error != null) {
                return ResponseData.error(error);
            } else if (!tokenCacheService.isTokenValid(token)) {
                return ResponseData.error(token.getError());
            }
            return ResponseData.success(tokenMapper.mapResponse(token));
        } catch (Exception ex) {
            log.error("Unknown error occurred while getting token", ex);
            return ResponseData.error(Errors.internalServerError(ex.getLocalizedMessage()));
        }
    }

    public ResponseData<?> evictToken(String clientId) {
        try {
            log.info("Evicting token from cache with clientId --- {}", clientId);
            tokenCacheService.evictTokenB2b(clientId);
            return ResponseData.success();
        } catch (Exception ex) {
            log.error("Unknown error occurred while evicting cached token", ex);
            return ResponseData.error(Errors.internalServerError(ex.getLocalizedMessage()));
        }
    }
}