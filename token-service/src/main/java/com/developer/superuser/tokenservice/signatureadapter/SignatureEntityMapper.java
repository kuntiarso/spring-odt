package com.developer.superuser.tokenservice.signatureadapter;

import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import com.developer.superuser.tokenservice.signature.Signature;
import org.springframework.stereotype.Component;

@Component
public class SignatureEntityMapper {
    public SignatureEntity toEntityNonSnap(Signature signature) {
        return SignatureEntity.builder()
                .setApiType(ApiType.NONSNAP)
                .setRequestId(signature.getRequestId())
                .setTargetEndpoint(signature.getTargetEndpoint())
                .setStringToSign(signature.getStringToSign())
                .setDigest(signature.getDigest())
                .setSignature(signature.getSignature())
                .build();
    }

    public SignatureEntity toEntitySymmetric(Signature signature) {
        return SignatureEntity.builder()
                .setApiType(ApiType.SNAP)
                .setSigType(SignatureType.SYMMETRIC)
                .setRequestId(signature.getRequestId())
                .setHttpMethod(signature.getHttpMethod())
                .setHttpMethod(signature.getTargetEndpoint())
                .setStringToSign(signature.getStringToSign())
                .setSignature(signature.getSignature())
                .build();
    }

    public SignatureEntity toEntityAsymmetric(Signature signature) {
        return SignatureEntity.builder()
                .setApiType(ApiType.SNAP)
                .setSigType(SignatureType.ASYMMETRIC)
                .setRequestId(signature.getRequestId())
                .setHttpMethod(signature.getHttpMethod())
                .setTargetEndpoint(signature.getTargetEndpoint())
                .setStringToSign(signature.getStringToSign())
                .setSignature(signature.getSignature())
                .build();
    }

    public Signature toSignature(SignatureEntity entity) {
        return Signature.builder()
                .setId(entity.getId())
                .setApiType(entity.getApiType())
                .setSigType(entity.getSigType())
                .setRequestId(entity.getRequestId())
                .setHttpMethod(entity.getHttpMethod())
                .setTargetEndpoint(entity.getTargetEndpoint())
                .setDigest(entity.getDigest())
                .setStringToSign(entity.getStringToSign())
                .setSignature(entity.getSignature())
                .setCreatedAt(entity.getCreatedAt())
                .setCreatedBy(entity.getCreatedBy())
                .setUpdatedAt(entity.getUpdatedAt())
                .setUpdatedBy(entity.getUpdatedBy())
                .setDeleted(entity.isDeleted())
                .setDeletedAt(entity.getDeletedAt())
                .setDeletedBy(entity.getDeletedBy())
                .setVersion(entity.getVersion())
                .build();
    }
}