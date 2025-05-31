package com.developer.superuser.tokenservice.core.config;

import com.developer.superuser.tokenservice.signature.DefaultSignatureServiceAdapter;
import com.developer.superuser.tokenservice.signature.SignatureService;
import com.developer.superuser.tokenservice.signatureadapter.SignatureEntityMapper;
import com.developer.superuser.tokenservice.signatureadapter.SignatureRepository;
import com.developer.superuser.tokenservice.signatureadapter.SignatureServiceAdapter;
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
}