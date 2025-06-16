package com.developer.superuser.tokenservice.token;

import com.developer.superuser.tokenservice.core.enumeration.TokenType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Token implements Serializable {
    @Serial
    private static final long serialVersionUID = 3_458_815_117_668_012_404L;
    private String clientId;
    private String signature;
    private String timestamp;
    private TokenType dokuTokenType;
    private String grantType;
    private String authCode;
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