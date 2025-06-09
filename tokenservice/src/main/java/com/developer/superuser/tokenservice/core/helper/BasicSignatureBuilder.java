package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.core.utility.DateUtility;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import org.springframework.stereotype.Component;

@Component
public class BasicSignatureBuilder implements GenericHelper<SignatureRequestDto, String> {
    @Override
    public String execute(SignatureRequestDto request) {
        request.setTimestamp(DateUtility.getCurrentTimestamp());
        return request.getClientId() + "|" + request.getTimestamp();
    }
}