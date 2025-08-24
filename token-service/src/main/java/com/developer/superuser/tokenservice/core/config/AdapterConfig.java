package com.developer.superuser.tokenservice.core.config;

import com.developer.superuser.shared.project.springodt.sign.Basic;
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
    public TokenApiService tokenApiService(Basic basic, SignMapper signMapper, TokenApi tokenApi) {
        return new TokenApiServiceAdapter(basic, signMapper, tokenApi);
    }

    @Bean
    public TokenCacheService tokenCacheService(TokenApiService tokenApiService) {
        return new TokenCacheServiceAdapter(tokenApiService);
    }
}