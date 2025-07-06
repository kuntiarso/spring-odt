package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.tokenservice.TokenServiceConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.Instant;

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
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = TokenServiceConstant.DATE_TIME_FORMAT, timezone = TokenServiceConstant.DATE_TIME_ZONE)
    private Instant timestamp;
}