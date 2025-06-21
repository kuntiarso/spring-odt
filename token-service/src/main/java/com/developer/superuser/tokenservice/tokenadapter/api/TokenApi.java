package com.developer.superuser.tokenservice.tokenadapter.api;

import com.developer.superuser.tokenservice.TokenServiceConstant;
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

    public Token fetchB2b(Token token) {
        log.info("Building fetchB2b request body");
        Map<String, Object> body = Map.of("grantType", token.getGrantType());
        log.info("Printing fetchB2b request body --- {}", body);
        return dokuRestClient.post()
                .uri(dokuConfig.getApi().getEndpoint().get("access-token"), token.getDokuTokenType().label)
                .header(TokenServiceConstant.HEADER_CLIENT_KEY, token.getClientId())
                .header(TokenServiceConstant.HEADER_SIGNATURE, token.getSignature())
                .header(TokenServiceConstant.HEADER_TIMESTAMP, token.getTimestamp())
                .body(body)
                .retrieve()
                .body(Token.class);
    }
}