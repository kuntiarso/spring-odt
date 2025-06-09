package com.developer.superuser.tokenservice.token;

import com.developer.superuser.tokenservice.core.enumeration.TokenType;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class Token implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String clientId;
    private String signature;
    private String timestamp;
    private TokenType dokuTokenType;
    private String grantType;
    private String authCode;
    // ****************************
    private String responseCode;
    private String responseMessage;
    private String tokenType;
    private String accessToken;
    private String accessTokenExpiryTime;
    private String refreshToken;
    private String refreshTokenExpiryTime;
    private int expiresIn;
    private String additionalInfo;
}