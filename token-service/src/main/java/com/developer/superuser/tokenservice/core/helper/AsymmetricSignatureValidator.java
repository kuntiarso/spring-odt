package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.core.enumeration.AlgoType;
import com.developer.superuser.tokenservice.core.enumeration.SignType;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class AsymmetricSignatureValidator implements GenericHelper<SignatureRequestDto, Void> {
    @Override
    public Void execute(SignatureRequestDto request) {
        Preconditions.checkNotNull(request, "request must not be null");
        Preconditions.checkNotNull(request.getSignType(), "signType must not be null");
        Preconditions.checkArgument(SignType.SNAP.equals(request.getSignType()), "signType value is unknown");
        Preconditions.checkNotNull(request.getAlgoType(), "algoType must not be null");
        Preconditions.checkArgument(AlgoType.ASYMMETRIC.equals(request.getAlgoType()), "algoType value is unknown");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getRequestId()), "requestId must not be null or empty");
        Preconditions.checkNotNull(request.getHttpMethod(), "httpMethod must not be null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getTargetEndpoint()), "targetEndpoint must not be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getDigest()), "digest must not be null or empty");
        return null;
    }
}