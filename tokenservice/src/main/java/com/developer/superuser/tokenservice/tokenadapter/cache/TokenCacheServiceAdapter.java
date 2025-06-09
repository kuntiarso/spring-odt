package com.developer.superuser.tokenservice.tokenadapter.cache;

import com.developer.superuser.tokenservice.TokenserviceConstant;
import com.developer.superuser.tokenservice.token.Token;
import com.developer.superuser.tokenservice.token.TokenApiService;
import com.developer.superuser.tokenservice.token.TokenCacheService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@RequiredArgsConstructor
@Slf4j
public class TokenCacheServiceAdapter implements TokenCacheService {
    private final TokenApiService tokenApiService;

    @Override
    @Cacheable(value = TokenserviceConstant.CACHE_TOKEN_B2B, key = "#token.clientId")
    public Token getOrFetchTokenB2b(Token token) {
        log.info("Cache is not available for token b2b, then fetching from doku api");
        return tokenApiService.fetchTokenB2b(token);
    }

    @Override
    @CacheEvict(value = TokenserviceConstant.CACHE_TOKEN_B2B, key = "#clientId")
    public void evictTokenB2b(String clientId) {
        log.info("Evicting token b2b from cache for clientId --- {}", clientId);
    }

    @Override
    public boolean isTokenB2bValid(Token token) {
        log.info("Validating token b2b");
        return token != null && !Strings.isNullOrEmpty(token.getAccessToken());
    }
}