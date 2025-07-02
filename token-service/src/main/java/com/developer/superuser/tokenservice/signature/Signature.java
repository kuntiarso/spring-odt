package com.developer.superuser.tokenservice.signature;

import com.developer.superuser.shared.audit.StandardAuditable;
import com.developer.superuser.tokenservice.core.enumeration.SignType;
import com.developer.superuser.tokenservice.core.enumeration.GrantType;
import com.developer.superuser.tokenservice.core.enumeration.AlgoType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Signature extends StandardAuditable {
    private Long id;
    private String requestId;
    private SignType signType;
    private AlgoType algoType;
    private String httpMethod;
    private String targetEndpoint;
    private String tokenB2b;
    private GrantType grantType;
    private String authCode;
    private String refreshToken;
    private String digest;
    private String stringToSign;
    private String signature;
    private String timestamp;
    private HttpStatus status;
}