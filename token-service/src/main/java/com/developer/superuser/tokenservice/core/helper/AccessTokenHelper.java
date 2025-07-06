package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.shared.helper.Validator;
import com.developer.superuser.tokenservice.tokenresource.TokenRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenHelper implements Validator<TokenRequestDto, Void> {
    @Override
    public Void validate(TokenRequestDto request) {
        Preconditions.checkNotNull(request, "request must not be null");
        Preconditions.checkNotNull(request.getTokenType(), "tokenType must not be null");
        Preconditions.checkNotNull(request.getGrantType(), "grantType must not be null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getClientId()), "clientId must not be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getSignature()), "signature must not be null or empty");
        Preconditions.checkNotNull(request.getTimestamp(), "timestamp must not be null");
        return null;
    }
}