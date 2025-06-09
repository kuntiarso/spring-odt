package com.developer.superuser.tokenservice.tokenresource;

import com.developer.superuser.tokenservice.token.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenCoreMapper {
    public Token map(TokenRequestDto request) {
        return Token.builder()
                .clientId(request.getClientId())
                .signature(request.getSignature())
                .grantType(request.getGrantType().label)
                .timestamp(request.getTimestamp())
                .build();
    }

    public TokenResponseDto mapResponse(Token token) {
        return TokenResponseDto.builder()
                .responseCode(token.getResponseCode())
                .responseMessage(token.getResponseMessage())
                .tokenType(token.getTokenType())
                .accessToken(token.getAccessToken())
                .accessTokenExpiryTime(token.getAccessTokenExpiryTime())
                .refreshToken(token.getRefreshToken())
                .refreshTokenExpiryTime(token.getRefreshTokenExpiryTime())
                .expiresIn(token.getExpiresIn())
                .additionalInfo(token.getAdditionalInfo())
                .build();
    }
}