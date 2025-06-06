package com.developer.superuser.tokenservice.core.helper;

import com.developer.superuser.tokenservice.core.utility.DateUtility;
import com.developer.superuser.tokenservice.signatureresource.SignatureRequestDto;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class NonSnapSignatureBuilder implements GenericHelper<SignatureRequestDto, String> {
    private static final String CLIENT_ID = "Client-Id";
    private static final String REQUEST_ID = "Request-Id";
    private static final String REQUEST_TIMESTAMP = "Request-Timestamp";
    private static final String REQUEST_TARGET = "Request-Target";
    private static final String DIGEST = "Digest";
    private static final String COLON_SYMBOL = ":";
    private static final String NEW_LINE = "\n";

    @Override
    public String execute(SignatureRequestDto request) {
        request.setTimestamp(DateUtility.getCurrentTimestamp());
        StringBuilder sb = new StringBuilder();
        sb.append(CLIENT_ID).append(COLON_SYMBOL).append(request.getClientId());
        sb.append(NEW_LINE);
        sb.append(REQUEST_ID).append(COLON_SYMBOL).append(request.getRequestId());
        sb.append(NEW_LINE);
        sb.append(REQUEST_TIMESTAMP).append(COLON_SYMBOL).append(request.getTimestamp());
        sb.append(NEW_LINE);
        sb.append(REQUEST_TARGET).append(COLON_SYMBOL).append(request.getTargetEndpoint());
        if (HttpMethod.POST.matches(request.getHttpMethod()) || HttpMethod.PUT.matches(request.getHttpMethod())) {
            sb.append(NEW_LINE);
            sb.append(DIGEST).append(COLON_SYMBOL).append(request.getDigest());
        }
        return sb.toString();
    }
}