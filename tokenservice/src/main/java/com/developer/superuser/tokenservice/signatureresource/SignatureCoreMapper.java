package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import com.developer.superuser.tokenservice.signature.Signature;
import org.springframework.stereotype.Component;

@Component
public class SignatureCoreMapper {
    public Signature mapBasic(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .apiType(ApiType.BASIC)
                .stringToSign(stringToSign)
                .signature(signature)
                .timestamp(request.getTimestamp())
                .build();
    }

    public Signature mapNonSnap(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .apiType(ApiType.NONSNAP)
                .requestId(request.getRequestId())
                .httpMethod(request.getHttpMethod().name())
                .targetEndpoint(request.getTargetEndpoint())
                .digest(request.getDigest())
                .stringToSign(stringToSign)
                .signature("HMACSHA256=".concat(signature))
                .timestamp(request.getTimestamp())
                .build();
    }

    public Signature mapSymmetric(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .apiType(ApiType.SNAP)
                .sigType(SignatureType.SYMMETRIC)
                .requestId(request.getRequestId())
                .httpMethod(request.getHttpMethod().name())
                .targetEndpoint(request.getTargetEndpoint())
                .tokenB2b(request.getTokenB2b())
                .digest(request.getDigest())
                .stringToSign(stringToSign)
                .signature(signature)
                .timestamp(request.getTimestamp())
                .build();
    }

    public Signature mapAsymmetric(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .apiType(ApiType.SNAP)
                .sigType(SignatureType.ASYMMETRIC)
                .requestId(request.getRequestId())
                .httpMethod(request.getHttpMethod().name())
                .targetEndpoint(request.getTargetEndpoint())
                .digest(request.getDigest())
                .stringToSign(stringToSign)
                .signature(signature)
                .timestamp(request.getTimestamp())
                .build();
    }

    public SignatureResponseDto mapResponse(Signature signature) {
        return SignatureResponseDto.builder()
                .requestId(signature.getRequestId())
                .signature(signature.getSignature())
                .timestamp(signature.getTimestamp())
                .build();
    }
}