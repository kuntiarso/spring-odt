package com.developer.superuser.tokenservice.tokenadapter.api;

import com.developer.superuser.shared.project.springodt.sign.Basic;
import com.developer.superuser.shared.project.springodt.sign.Sign;
import com.developer.superuser.tokenservice.token.Token;
import com.developer.superuser.tokenservice.token.TokenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Slf4j
public class TokenApiServiceAdapter implements TokenApiService {
    private final Basic basic;
    private final SignMapper signMapper;
    private final TokenApi tokenApi;

    @Override
    public Token fetchTokenB2b(Token token) {
        Sign sign = basic.generate(signMapper.toBasicSign(token));
        log.info("Printing basic sign result --- {}", sign);
        token.setSignature(sign.getSignature());
        token.setTimestamp(sign.getTimestamp());
        return tokenApi.dokuFetchB2b(token);
    }

    @Override
    public Token fetchTokenB2b2c(Token token) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}