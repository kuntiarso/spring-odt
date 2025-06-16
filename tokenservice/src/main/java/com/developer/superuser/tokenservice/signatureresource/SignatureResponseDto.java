package com.developer.superuser.tokenservice.signatureresource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode
@ToString
public class SignatureResponseDto {
    private String requestId;
    private String signature;
    private String timestamp;
}