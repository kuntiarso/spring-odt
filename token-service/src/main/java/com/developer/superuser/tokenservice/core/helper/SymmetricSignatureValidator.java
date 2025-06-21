package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.core.enumeration.ApiType;
import com.developer.superuser.tokenservice.core.enumeration.SignatureType;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class SymmetricSignatureValidator implements GenericHelper<SignatureRequestDto, Void> {
    @Override
    public Void execute(SignatureRequestDto request) {
        Preconditions.checkNotNull(request, "request body cannot be null");
        Preconditions.checkNotNull(request.getApiType(), "apiType cannot be null");
        Preconditions.checkArgument(ApiType.SNAP.equals(request.getApiType()), "apiType value is invalid");
        Preconditions.checkNotNull(request.getSigType(), "sigType cannot be null");
        Preconditions.checkArgument(SignatureType.SYMMETRIC.equals(request.getSigType()), "sigType value is invalid");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getRequestId()), "requestId cannot be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getClientId()), "clientId cannot be null or empty");
        Preconditions.checkNotNull(request.getHttpMethod(), "httpMethod cannot be null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getTargetEndpoint()), "targetEndpoint cannot be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getTokenB2b()), "tokenB2b cannot be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getDigest()), "digest cannot be null or empty");
        return null;
    }
}