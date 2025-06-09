package com.developer.superuser.tokenservice.tokenadapter.api;

import com.developer.superuser.tokenservice.TokenserviceConstant;
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
    private final DokuConfigProperties dokuConfigProperties;

    public Token fetchB2b(Token token) {
        log.info("Building fetchB2b request body");
        Map<String, Object> body = Map.of("grantType", token.getGrantType());
        log.info("Printing fetchB2b request body --- {}", body);
        return dokuRestClient.post()
                .uri(dokuConfigProperties.getEndpoint().getAccessToken(), token.getDokuTokenType().label)
                .header(TokenserviceConstant.HEADER_CLIENT_KEY, token.getClientId())
                .header(TokenserviceConstant.HEADER_SIGNATURE, token.getSignature())
                .header(TokenserviceConstant.HEADER_TIMESTAMP, token.getTimestamp())
                .body(body)
                .retrieve()
                .body(Token.class);
    }
}