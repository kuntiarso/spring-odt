package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpMethod;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString
public class SignatureRequestDto {
    private ApiType apiType;
    private SignatureType sigType;
    private String clientId;
    private String requestId;
    private HttpMethod httpMethod;
    private String targetEndpoint;
    private String tokenB2b;
    private String digest;
    @JsonIgnore
    private String timestamp;
}
