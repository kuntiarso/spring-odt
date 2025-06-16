package com.developer.superuser.tokenservice.signature;

import com.developer.superuser.shared.audit.StandardAuditable;
import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.GrantType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Signature extends StandardAuditable implements Serializable {
    @Serial
    private static final long serialVersionUID = 5_077_897_682_293_664_067L;
    private Long id;
    private ApiType apiType;
    private SignatureType sigType;
    private String requestId;
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