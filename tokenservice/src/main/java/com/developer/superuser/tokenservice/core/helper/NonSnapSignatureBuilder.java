package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.TokenserviceConstant;
import com.developer.superuser.tokenservice.core.utility.DateUtility;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class NonSnapSignatureBuilder implements GenericHelper<SignatureRequestDto, String> {
    @Override
    public String execute(SignatureRequestDto request) {
        request.setTimestamp(DateUtility.getCurrentTimestamp());
        StringBuilder sb = new StringBuilder();
        sb.append(TokenserviceConstant.NON_SNAP_CLIENT_ID);
        sb.append(TokenserviceConstant.SYMBOL_COLON);
        sb.append(request.getClientId());
        sb.append(TokenserviceConstant.SYMBOL_NEW_LINE);
        sb.append(TokenserviceConstant.NON_SNAP_REQUEST_ID);
        sb.append(TokenserviceConstant.SYMBOL_COLON);
        sb.append(request.getRequestId());
        sb.append(TokenserviceConstant.SYMBOL_NEW_LINE);
        sb.append(TokenserviceConstant.NON_SNAP_TIMESTAMP);
        sb.append(TokenserviceConstant.SYMBOL_COLON);
        sb.append(request.getTimestamp());
        sb.append(TokenserviceConstant.SYMBOL_NEW_LINE);
        sb.append(TokenserviceConstant.NON_SNAP_TARGET_ENDPOINT);
        sb.append(TokenserviceConstant.SYMBOL_COLON);
        sb.append(request.getTargetEndpoint());
        if (HttpMethod.POST.equals(request.getHttpMethod()) || HttpMethod.PUT.equals(request.getHttpMethod())) {
            sb.append(TokenserviceConstant.SYMBOL_NEW_LINE);
            sb.append(TokenserviceConstant.NON_SNAP_DIGEST);
            sb.append(TokenserviceConstant.SYMBOL_COLON);
            sb.append(request.getDigest());
        }
        return sb.toString();
    }
}