package com.developer.superuser.paymentservice.tokensvc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
    private String dokuTokenType;
    private String clientId;
    private String signature;
    private String grantType;
    private String timestamp;
    private String tokenType;
    private String accessToken;
    private String accessTokenExpiryTime;
    private String refreshToken;
    private String refreshTokenExpiryTime;
    private String expiresIn;
    private String additionalInfo;
}