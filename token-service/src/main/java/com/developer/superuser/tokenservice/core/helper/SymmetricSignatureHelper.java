package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.shared.helper.Builder;
import com.developer.superuser.shared.helper.Validator;
import com.developer.superuser.tokenservice.TokenServiceConstant;
import com.developer.superuser.tokenservice.core.enumeration.AlgoType;
import com.developer.superuser.tokenservice.core.enumeration.SignType;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class SymmetricSignatureHelper implements Validator<SignatureRequestDto, Void>, Builder<SignatureRequestDto, String> {
    @Override
    public Void validate(SignatureRequestDto request) {
        Preconditions.checkNotNull(request, "request must not be null");
        Preconditions.checkNotNull(request.getSignType(), "signType must not be null");
        Preconditions.checkArgument(SignType.SNAP.equals(request.getSignType()), "signType value is unknown");
        Preconditions.checkNotNull(request.getAlgoType(), "algoType must not be null");
        Preconditions.checkArgument(AlgoType.SYMMETRIC.equals(request.getAlgoType()), "algoType value is unknown");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getRequestId()), "requestId must not be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getClientId()), "clientId must not be null or empty");
        Preconditions.checkNotNull(request.getHttpMethod(), "httpMethod must not be null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getTargetEndpoint()), "targetEndpoint must not be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getToken()), "token must not be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getDigest()), "digest must not be null or empty");
        return null;
    }

    @Override
    public String build(SignatureRequestDto request) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getHttpMethod().name());
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getTargetEndpoint());
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getToken());
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getDigest());
        sb.append(TokenServiceConstant.SYMBOL_COLON);
        sb.append(request.getTimestamp());
        return sb.toString();
    }
}