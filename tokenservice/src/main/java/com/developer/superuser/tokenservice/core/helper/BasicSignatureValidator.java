package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class BasicSignatureValidator implements GenericHelper<SignatureRequestDto, Void> {
    @Override
    public Void execute(SignatureRequestDto request) {
        Preconditions.checkNotNull(request, "request object cannot be null");
        Preconditions.checkNotNull(request.getApiType(), "apiType cannot be null");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getClientId()), "clientId cannot be null or empty");
        return null;
    }
}