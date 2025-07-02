package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class NonSnapSignatureValidator implements GenericHelper<SignatureRequestDto, Void> {
    @Override
    public Void execute(SignatureRequestDto request) {
        Preconditions.checkNotNull(request, "request must not be null");
        Preconditions.checkNotNull(request.getSignType(), "signType must not be null");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getRequestId()), "requestId must not be null or empty");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getClientId()), "clientId must not be null or empty");
        Preconditions.checkNotNull(request.getHttpMethod(), "httpMethod must not be null");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getTargetEndpoint()), "targetEndpoint must not be null or empty");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getDigest()), "digest must not be null or empty");
        return null;
    }
}