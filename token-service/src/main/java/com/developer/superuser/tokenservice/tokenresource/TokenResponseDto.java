package com.developer.superuser.tokenservice.tokenresource;

import com.developer.superuser.tokenservice.core.data.ErrorData;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponseDto {
    private String tokenScheme;
    private String accessToken;
    private Instant accessTokenExpiryTime;
    private String refreshToken;
    private Instant refreshTokenExpiryTime;
    private Integer expiresIn;
    private String additionalInfo;
    private ErrorData error;
}