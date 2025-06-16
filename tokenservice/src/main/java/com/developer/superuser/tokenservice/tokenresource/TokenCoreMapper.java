package com.developer.superuser.tokenservice.tokenresource;

import com.developer.superuser.tokenservice.token.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenCoreMapper {
    public Token map(TokenRequestDto request) {
        return Token.builder()
                .setClientId(request.getClientId())
                .setSignature(request.getSignature())
                .setGrantType(request.getGrantType().label)
                .setTimestamp(request.getTimestamp())
                .build();
    }

    public TokenResponseDto mapResponse(Token token) {
        return TokenResponseDto.builder()
                .setResponseCode(token.getResponseCode())
                .setResponseMessage(token.getResponseMessage())
                .setTokenType(token.getTokenType())
                .setAccessToken(token.getAccessToken())
                .setAccessTokenExpiryTime(token.getAccessTokenExpiryTime())
                .setRefreshToken(token.getRefreshToken())
                .setRefreshTokenExpiryTime(token.getRefreshTokenExpiryTime())
                .setExpiresIn(token.getExpiresIn())
                .setAdditionalInfo(token.getAdditionalInfo())
                .build();
    }
}