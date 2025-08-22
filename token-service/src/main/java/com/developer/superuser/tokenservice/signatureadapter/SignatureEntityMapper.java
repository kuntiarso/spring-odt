package com.developer.superuser.tokenservice.signatureadapter;

import com.developer.superuser.tokenservice.core.enumeration.AlgoType;
import com.developer.superuser.tokenservice.core.enumeration.SignType;
import com.developer.superuser.tokenservice.signature.Signature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class SignatureEntityMapper {
    public SignatureEntity toEntityNonSnap(Signature signature) {
        return SignatureEntity.builder()
                .setSignType(SignType.NON_SNAP)
                .setRequestId(signature.getRequestId())
                .setHttpMethod(signature.getHttpMethod().name())
                .setTargetEndpoint(signature.getTargetEndpoint())
                .setStringToSign(signature.getStringToSign())
                .setDigest(signature.getDigest())
                .setSignature(signature.getSignature())
                .build();
    }

    public SignatureEntity toEntitySymmetric(Signature signature) {
        return SignatureEntity.builder()
                .setSignType(SignType.SNAP)
                .setAlgoType(AlgoType.SYMMETRIC)
                .setRequestId(signature.getRequestId())
                .setHttpMethod(signature.getHttpMethod().name())
                .setTargetEndpoint(signature.getTargetEndpoint())
                .setStringToSign(signature.getStringToSign())
                .setSignature(signature.getSignature())
                .build();
    }

    public SignatureEntity toEntityAsymmetric(Signature signature) {
        return SignatureEntity.builder()
                .setSignType(SignType.SNAP)
                .setAlgoType(AlgoType.ASYMMETRIC)
                .setRequestId(signature.getRequestId())
                .setHttpMethod(signature.getHttpMethod().name())
                .setTargetEndpoint(signature.getTargetEndpoint())
                .setStringToSign(signature.getStringToSign())
                .setSignature(signature.getSignature())
                .build();
    }

    public Signature toSignature(SignatureEntity entity) {
        return Signature.builder()
                .setId(entity.getId())
                .setSignType(entity.getSignType())
                .setAlgoType(entity.getAlgoType())
                .setRequestId(entity.getRequestId())
                .setHttpMethod(HttpMethod.valueOf(entity.getHttpMethod()))
                .setTargetEndpoint(entity.getTargetEndpoint())
                .setDigest(entity.getDigest())
                .setStringToSign(entity.getStringToSign())
                .setSignature(entity.getSignature())
                .setTimestamp(entity.getCreatedAt())
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