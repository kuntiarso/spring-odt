package com.developer.superuser.tokenservice.tokenresource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponseDto {
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