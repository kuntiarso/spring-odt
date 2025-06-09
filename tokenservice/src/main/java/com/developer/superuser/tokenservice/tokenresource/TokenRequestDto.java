package com.developer.superuser.tokenservice.tokenresource;

import com.developer.superuser.tokenservice.core.enumeration.GrantType;
import com.developer.superuser.tokenservice.core.enumeration.TokenType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class TokenRequestDto {
    private TokenType dokuTokenType;
    private String clientId;
    private String signature;
    private GrantType grantType;
    private String timestamp;
}