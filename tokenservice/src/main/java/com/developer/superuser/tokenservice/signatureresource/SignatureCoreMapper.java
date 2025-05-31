package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.signature.Signature;
import org.springframework.stereotype.Component;

@Component
public class SignatureCoreMapper {
    public Signature mapNonSnap(SignatureRequestDto request, String signature) {
        return Signature.builder()
                .apiType(ApiType.NONSNAP)
                .requestId(request.getRequestId())
                .httpMethod(request.getHttpMethod())
                .targetEndpoint(request.getTargetEndpoint())
                .digest(request.getDigest())
                .signature("HMACSHA256=".concat(signature))
                .build();
    }

    public SignatureResponseDto mapResponse(Signature signature) {
        return SignatureResponseDto.builder()
                .requestId(signature.getRequestId())
                .signature(signature.getSignature())
                .build();
    }
}