package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.tokenservice.core.enumeration.SignType;
import com.developer.superuser.tokenservice.core.enumeration.AlgoType;
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
    private SignType signType;
    private AlgoType algoType;
    private String clientId;
    private String requestId;
    private HttpMethod httpMethod;
    private String targetEndpoint;
    private String tokenB2b;
    private String digest;
    @JsonIgnore
    private String timestamp;
}
