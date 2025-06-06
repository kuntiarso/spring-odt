package com.developer.superuser.tokenservice.signatureadapter;

import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import com.developer.superuser.tokenservice.signature.Signature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SignatureEntityMapper {
    public SignatureEntity toEntityNonSnap(Signature signature) {
        return SignatureEntity.builder()
                .apiType(ApiType.NONSNAP)
                .requestId(signature.getRequestId())
                .targetEndpoint(signature.getTargetEndpoint())
                .digest(signature.getDigest())
                .signature(signature.getSignature())
                .build();
    }

    public SignatureEntity toEntitySymmetric(Signature signature) {
        return SignatureEntity.builder()
                .apiType(ApiType.SNAP)
                .sigType(SignatureType.SYMMETRIC)
                .requestId(signature.getRequestId())
                .httpMethod(signature.getHttpMethod())
                .targetEndpoint(signature.getTargetEndpoint())
                .stringToSign(signature.getStringToSign())
                .signature(signature.getSignature())
                .build();
    }

    public SignatureEntity toEntityAsymmetric(Signature signature) {
        return SignatureEntity.builder()
                .apiType(ApiType.SNAP)
                .sigType(SignatureType.ASYMMETRIC)
                .requestId(signature.getRequestId())
                .httpMethod(signature.getHttpMethod())
                .targetEndpoint(signature.getTargetEndpoint())
                .stringToSign(signature.getStringToSign())
                .signature(signature.getSignature())
                .build();
    }

    public Signature toSignature(SignatureEntity entity) {
        return Signature.builder()
                .id(entity.getId())
                .apiType(entity.getApiType())
                .sigType(entity.getSigType())
                .requestId(entity.getRequestId())
                .httpMethod(entity.getHttpMethod())
                .targetEndpoint(entity.getTargetEndpoint())
                .digest(entity.getDigest())
                .stringToSign(entity.getStringToSign())
                .signature(entity.getSignature())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }
}