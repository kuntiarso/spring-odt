package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class NonSnapSignatureValidator implements GenericHelper<SignatureRequestDto, Void> {
    @Override
    public Void execute(SignatureRequestDto request) {
        Preconditions.checkNotNull(request, "request body cannot be null");
        Preconditions.checkNotNull(request.getApiType(), "apiType cannot be null");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getRequestId()), "requestId cannot be null or empty");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getClientId()), "clientId cannot be null or empty");
        Preconditions.checkNotNull(request.getHttpMethod(), "httpMethod cannot be null");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getTargetEndpoint()), "targetEndpoint cannot be null or empty");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getDigest()), "digest cannot be null or empty");
        return null;
    }
}