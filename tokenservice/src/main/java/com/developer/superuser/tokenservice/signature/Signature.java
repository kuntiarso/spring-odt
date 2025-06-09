package com.developer.superuser.tokenservice.signature;

import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Signature {
    private Long id;
    private ApiType apiType;
    private SignatureType sigType;
    private String requestId;
    private String httpMethod;
    private String targetEndpoint;
    private String digest;
    private String stringToSign;
    private String signature;
    private String timestamp;
    private HttpStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}