package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.tokenresource.TokenRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class GetAccessTokenValidator implements GenericHelper<TokenRequestDto, Void> {
    @Override
    public Void execute(TokenRequestDto request) {
        Preconditions.checkNotNull(request, "request must not be null");
        Preconditions.checkNotNull(request.getDokuTokenType(), "dokuTokenType must not be null");
        Preconditions.checkNotNull(request.getGrantType(), "grantType must not be null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getClientId()), "clientId must not be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getSignature()), "signature must not be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getTimestamp()), "timestamp must not be null or empty");
        return null;
    }
}