package com.developer.superuser.tokenservice.tokenadapter.api;

import com.developer.superuser.shared.utility.Dates;
import com.developer.superuser.tokenservice.TokenServiceConstant;
import com.developer.superuser.tokenservice.core.enumeration.TokenType;
import com.developer.superuser.tokenservice.core.helper.TokenApiHelper;
import com.developer.superuser.tokenservice.core.property.DokuConfigProperties;
import com.developer.superuser.tokenservice.token.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenApi {
    private final RestClient dokuRestClient;
    private final DokuConfigProperties dokuConfig;
    private final TokenApiHelper tokenApiHelper;

    public Token fetchB2b(Token token) {
        Map<String, Object> body = Map.of("grantType", token.getGrantType());
        log.info("Printing fetchB2b request body --- {}", body);
        return tokenApiHelper.execute(() ->
                dokuRestClient.post()
                        .uri(dokuConfig.getApi().getEndpoint().get("access-token"), TokenType.B2B.label)
                        .header(TokenServiceConstant.HEADER_CLIENT_KEY, token.getClientId())
                        .header(TokenServiceConstant.HEADER_SIGNATURE, token.getSignature())
                        .header(TokenServiceConstant.HEADER_TIMESTAMP, Dates.toInstantString(token.getTimestamp()))
                        .body(body)
                        .retrieve()
                        .body(Token.class)
        );
    }
}