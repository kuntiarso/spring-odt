package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.TokenServiceConstant;
import com.developer.superuser.tokenservice.core.utility.DateUtility;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import org.springframework.stereotype.Component;

@Component
public class AsymmetricSignatureBuilder implements GenericHelper<SignatureRequestDto, String> {
    @Override
    public String execute(SignatureRequestDto request) {
        request.setTimestamp(DateUtility.getCurrentTimestamp());
        StringBuilder sb = new StringBuilder();
        sb.append(request.getHttpMethod().name());
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getTargetEndpoint());
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getDigest());
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getTimestamp());
        return "";
    }
}