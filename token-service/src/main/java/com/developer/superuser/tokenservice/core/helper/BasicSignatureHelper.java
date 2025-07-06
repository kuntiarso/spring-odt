package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.shared.helper.Builder;
import com.developer.superuser.shared.helper.Validator;
import com.developer.superuser.shared.utility.Dates;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class BasicSignatureHelper implements Validator<SignatureRequestDto, Void>, Builder<SignatureRequestDto, String> {
    @Override
    public Void validate(SignatureRequestDto request) {
        Preconditions.checkNotNull(request, "request must not be null");
        Preconditions.checkNotNull(request.getSignType(), "signType must not be null");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getClientId()), "clientId must not be null or empty");
        return null;
    }

    @Override
    public String build(SignatureRequestDto request) {
        return request.getClientId() + "|" + Dates.toInstantString(request.getTimestamp());
    }
}