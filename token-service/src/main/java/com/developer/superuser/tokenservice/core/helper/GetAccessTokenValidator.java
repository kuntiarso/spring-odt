package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.tokenresource.TokenRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class GetAccessTokenValidator implements GenericHelper<TokenRequestDto, Void> {
    @Override
    public Void execute(TokenRequestDto request) {
        Preconditions.checkNotNull(request, "request object cannot be null");
        Preconditions.checkNotNull(request.getDokuTokenType(), "dokuTokenType cannot be null");
        Preconditions.checkNotNull(request.getGrantType(), "grantType cannot be null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getClientId()), "clientId cannot be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getSignature()), "signature cannot be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getTimestamp()), "timestamp cannot be null or empty");
        return null;
    }
}