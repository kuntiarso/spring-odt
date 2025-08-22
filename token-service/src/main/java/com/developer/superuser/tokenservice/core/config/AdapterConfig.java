package com.developer.superuser.tokenservice.core.config;

import com.developer.superuser.shared.project.springodt.sign.Basic;
import com.developer.superuser.tokenservice.signature.SignatureService;
import com.developer.superuser.tokenservice.signatureadapter.SignatureEntityMapper;
import com.developer.superuser.tokenservice.signatureadapter.SignatureRepository;
import com.developer.superuser.tokenservice.signatureadapter.SignatureServiceAdapter;
import com.developer.superuser.tokenservice.token.TokenApiService;
import com.developer.superuser.tokenservice.token.TokenCacheService;
import com.developer.superuser.tokenservice.tokenadapter.api.SignMapper;
import com.developer.superuser.tokenservice.tokenadapter.api.TokenApi;
import com.developer.superuser.tokenservice.tokenadapter.api.TokenApiServiceAdapter;
import com.developer.superuser.tokenservice.tokenadapter.cache.TokenCacheServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {
    @Bean
    public SignatureService signatureService(SignatureRepository signatureRepository, SignatureEntityMapper signatureEntityMapper) {
        return new SignatureServiceAdapter(signatureRepository, signatureEntityMapper);
    }

    @Bean
    public TokenApiService tokenApiService(Basic basic, SignMapper signMapper, TokenApi tokenApi) {
        return new TokenApiServiceAdapter(basic, signMapper, tokenApi);
    }

    @Bean
    public TokenCacheService tokenCacheService(TokenApiService tokenApiService) {
        return new TokenCacheServiceAdapter(tokenApiService);
    }
}