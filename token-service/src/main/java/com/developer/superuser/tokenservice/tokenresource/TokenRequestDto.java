package com.developer.superuser.tokenservice.tokenresource;

import com.developer.superuser.tokenservice.core.enumeration.GrantType;
import com.developer.superuser.tokenservice.core.enumeration.TokenType;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString
public class TokenRequestDto {
    private String clientId;
    private TokenType tokenType;
    private GrantType grantType;
    private Instant timestamp;
    private String signature;
}