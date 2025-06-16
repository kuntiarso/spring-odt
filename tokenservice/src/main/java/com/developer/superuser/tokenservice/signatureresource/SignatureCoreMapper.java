package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import com.developer.superuser.tokenservice.signature.Signature;
import org.springframework.stereotype.Component;

@Component
public class SignatureCoreMapper {
    public Signature mapBasic(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .setApiType(ApiType.BASIC)
                .setStringToSign(stringToSign)
                .setSignature(signature)
                .setTimestamp(request.getTimestamp())
                .build();
    }

    public Signature mapNonSnap(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .setApiType(ApiType.NONSNAP)
                .setRequestId(request.getRequestId())
                .setHttpMethod(request.getHttpMethod().name())
                .setTargetEndpoint(request.getTargetEndpoint())
                .setDigest(request.getDigest())
                .setStringToSign(stringToSign)
                .setSignature("HMACSHA256=".concat(signature))
                .setTimestamp(request.getTimestamp())
                .build();
    }

    public Signature mapSymmetric(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .setApiType(ApiType.SNAP)
                .setSigType(SignatureType.SYMMETRIC)
                .setRequestId(request.getRequestId())
                .setHttpMethod(request.getHttpMethod().name())
                .setTargetEndpoint(request.getTargetEndpoint())
                .setTokenB2b(request.getTokenB2b())
                .setDigest(request.getDigest())
                .setStringToSign(stringToSign)
                .setSignature(signature)
                .setTimestamp(request.getTimestamp())
                .build();
    }

    public Signature mapAsymmetric(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .setApiType(ApiType.SNAP)
                .setSigType(SignatureType.ASYMMETRIC)
                .setRequestId(request.getRequestId())
                .setHttpMethod(request.getHttpMethod().name())
                .setTargetEndpoint(request.getTargetEndpoint())
                .setDigest(request.getDigest())
                .setStringToSign(stringToSign)
                .setSignature(signature)
                .setTimestamp(request.getTimestamp())
                .build();
    }

    public SignatureResponseDto mapResponse(Signature signature) {
        return SignatureResponseDto.builder()
                .setRequestId(signature.getRequestId())
                .setSignature(signature.getSignature())
                .setTimestamp(signature.getTimestamp())
                .build();
    }
}