package com.developer.superuser.tokenservice.signatureresource;

import com.developer.superuser.tokenservice.core.enumeration.SignType;
import com.developer.superuser.tokenservice.core.enumeration.AlgoType;
import com.developer.superuser.tokenservice.signature.Signature;
import org.springframework.stereotype.Component;

@Component
public class SignatureCoreMapper {
    public Signature mapBasic(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .setSignType(SignType.BASIC)
                .setStringToSign(stringToSign)
                .setSignature(signature)
                .setTimestamp(request.getTimestamp())
                .build();
    }

    public Signature mapNonSnap(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .setSignType(SignType.NON_SNAP)
                .setRequestId(request.getRequestId())
                .setHttpMethod(request.getHttpMethod())
                .setTargetEndpoint(request.getTargetEndpoint())
                .setDigest(request.getDigest())
                .setStringToSign(stringToSign)
                .setSignature("HMACSHA256=".concat(signature))
                .setTimestamp(request.getTimestamp())
                .build();
    }

    public Signature mapSymmetric(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .setSignType(SignType.SNAP)
                .setAlgoType(AlgoType.SYMMETRIC)
                .setRequestId(request.getRequestId())
                .setHttpMethod(request.getHttpMethod())
                .setTargetEndpoint(request.getTargetEndpoint())
                .setToken(request.getToken())
                .setDigest(request.getDigest())
                .setStringToSign(stringToSign)
                .setSignature(signature)
                .setTimestamp(request.getTimestamp())
                .build();
    }

    public Signature mapAsymmetric(SignatureRequestDto request, String stringToSign, String signature) {
        return Signature.builder()
                .setSignType(SignType.SNAP)
                .setAlgoType(AlgoType.ASYMMETRIC)
                .setRequestId(request.getRequestId())
                .setHttpMethod(request.getHttpMethod())
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