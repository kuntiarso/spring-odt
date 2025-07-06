package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.shared.helper.Builder;
import com.developer.superuser.shared.helper.Validator;
import com.developer.superuser.tokenservice.TokenServiceConstant;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class NonSnapSignatureHelper implements Validator<SignatureRequestDto, Void>, Builder<SignatureRequestDto, String> {
    @Override
    public Void validate(SignatureRequestDto request) {
        Preconditions.checkNotNull(request, "request must not be null");
        Preconditions.checkNotNull(request.getSignType(), "signType must not be null");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getClientId()), "clientId must not be null or empty");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getRequestId()), "requestId must not be null or empty");
        Preconditions.checkNotNull(request.getHttpMethod(), "httpMethod must not be null");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getTargetEndpoint()), "targetEndpoint must not be null or empty");
        Preconditions.checkState(!Strings.isNullOrEmpty(request.getDigest()), "digest must not be null or empty");
        return null;
    }

    @Override
    public String build(SignatureRequestDto request) {
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