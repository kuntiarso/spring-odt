package com.developer.superuser.tokenservice.signatureresource;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class SignatureResponseDto {
    private String requestId;
    private String signature;
}