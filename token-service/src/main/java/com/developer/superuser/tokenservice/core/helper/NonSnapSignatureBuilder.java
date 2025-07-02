package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.TokenServiceConstant;
import com.developer.superuser.tokenservice.core.utility.DateUtil;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class NonSnapSignatureBuilder implements GenericHelper<SignatureRequestDto, String> {
    @Override
    public String execute(SignatureRequestDto request) {
        request.setTimestamp(DateUtil.getCurrentTimestamp());
        StringBuilder sb = new StringBuilder();
        sb.append(TokenServiceConstant.NON_SNAP_CLIENT_ID);
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getClientId());
        sb.append(TokenServiceConstant.SYMBOL_NEW_LINE);
        sb.append(TokenServiceConstant.NON_SNAP_REQUEST_ID);
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getRequestId());
        sb.append(TokenServiceConstant.SYMBOL_NEW_LINE);
        sb.append(TokenServiceConstant.NON_SNAP_TIMESTAMP);
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getTimestamp());
        sb.append(TokenServiceConstant.SYMBOL_NEW_LINE);
        sb.append(TokenServiceConstant.NON_SNAP_TARGET_ENDPOINT);
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getTargetEndpoint());
        if (HttpMethod.POST.equals(request.getHttpMethod()) || HttpMethod.PUT.equals(request.getHttpMethod())) {
            sb.append(TokenServiceConstant.SYMBOL_NEW_LINE);
            sb.append(TokenServiceConstant.NON_SNAP_DIGEST);
            sb.append(TokenServiceConstant.SYMBOL_COLON);
            sb.append(request.getDigest());
        }
        return sb.toString();
    }
}