package com.developer.superuser.tokenservice.core.config;

import com.developer.superuser.tokenservice.signature.DefaultSignatureServiceAdapter;
import com.developer.superuser.tokenservice.signature.SignatureService;
import com.developer.superuser.tokenservice.signatureadapter.SignatureEntityMapper;
import com.developer.superuser.tokenservice.signatureadapter.SignatureRepository;
import com.developer.superuser.tokenservice.signatureadapter.SignatureServiceAdapter;
import com.developer.superuser.tokenservice.token.DefaultTokenApiServiceAdapter;
import com.developer.superuser.tokenservice.token.DefaultTokenCacheServiceAdapter;
import com.developer.superuser.tokenservice.token.TokenApiService;
import com.developer.superuser.tokenservice.token.TokenCacheService;
import com.developer.superuser.tokenservice.tokenadapter.api.TokenApi;
import com.developer.superuser.tokenservice.tokenadapter.api.TokenApiServiceAdapter;
import com.developer.superuser.tokenservice.tokenadapter.cache.TokenCacheServiceAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {
    @Bean
    public SignatureService signatureService(SignatureRepository signatureRepository, SignatureEntityMapper signatureEntityMapper) {
        return new SignatureServiceAdapter(signatureRepository, signatureEntityMapper);
    }

    @Bean
    @ConditionalOnMissingBean(SignatureService.class)
    public SignatureService defaultSignatureService() {
        return new DefaultSignatureServiceAdapter();
    }

    @Bean
    public TokenApiService tokenApiService(TokenApi tokenApi) {
        return new TokenApiServiceAdapter(tokenApi);
    }

    @Bean
    @ConditionalOnMissingBean(TokenApiService.class)
    public TokenApiService defaultTokenApiService() {
        return new DefaultTokenApiServiceAdapter();
    }

    @Bean
    public TokenCacheService tokenCacheService(TokenApiService tokenApiService) {
        return new TokenCacheServiceAdapter(tokenApiService);
    }

    @Bean
    @ConditionalOnMissingBean(TokenCacheService.class)
    public TokenCacheService defaultTokenCacheService() {
        return new DefaultTokenCacheServiceAdapter();
    }
}