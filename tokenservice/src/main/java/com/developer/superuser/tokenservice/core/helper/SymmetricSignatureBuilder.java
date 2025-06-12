package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.TokenserviceConstant;
import com.developer.superuser.tokenservice.core.utility.DateUtility;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import org.springframework.stereotype.Component;

@Component
public class SymmetricSignatureBuilder implements GenericHelper<SignatureRequestDto, String> {
    @Override
    public String execute(SignatureRequestDto request) {
        request.setTimestamp(DateUtility.getCurrentTimestamp());
        StringBuilder sb = new StringBuilder();
        sb.append(request.getHttpMethod().name());
        sb.append(TokenserviceConstant.SYMBOL_COLON);
        sb.append(request.getTargetEndpoint());
        sb.append(TokenserviceConstant.SYMBOL_COLON);
        sb.append(request.getTokenB2b());
        sb.append(TokenserviceConstant.SYMBOL_COLON);
        sb.append(request.getDigest());
        sb.append(TokenserviceConstant.SYMBOL_COLON);
        sb.append(request.getTimestamp());
        return sb.toString();
    }
}