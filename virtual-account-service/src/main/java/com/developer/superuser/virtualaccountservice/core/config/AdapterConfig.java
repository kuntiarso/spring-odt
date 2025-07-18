package com.developer.superuser.virtualaccountservice.core.config;

import com.developer.superuser.shared.helper.Executor;
import com.developer.superuser.virtualaccountservice.core.property.DokuConfigProperties;
import com.developer.superuser.virtualaccountservice.vapayment.DefaultVaApiServiceAdapter;
import com.developer.superuser.virtualaccountservice.vapayment.DefaultVaPersistenceServiceAdapter;
import com.developer.superuser.virtualaccountservice.vapayment.VaApiService;
import com.developer.superuser.virtualaccountservice.vapayment.VaPersistenceService;
import com.developer.superuser.virtualaccountservice.vapaymentadapter.api.VaApi;
import com.developer.superuser.virtualaccountservice.vapaymentadapter.api.VaApiServiceAdapter;
import com.developer.superuser.virtualaccountservice.vapaymentadapter.db.VaPaymentDetailEntityMapper;
import com.developer.superuser.virtualaccountservice.vapaymentadapter.db.VaPersistenceServiceAdapter;
import com.developer.superuser.virtualaccountservice.vapaymentadapter.db.VaRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {
    @Bean
    public VaPersistenceService vaPersistenceService(VaRepository vaRepository, VaPaymentDetailEntityMapper vaPaymentDetailEntityMapper) {
        return new VaPersistenceServiceAdapter(vaRepository, vaPaymentDetailEntityMapper);
    }

    @Bean
    @ConditionalOnMissingBean(VaPersistenceService.class)
    public VaPersistenceService defaultVaPersistenceService() {
        return new DefaultVaPersistenceServiceAdapter();
    }

    @Bean
    public VaApiService vaApiService(VaApi vaApi, DokuConfigProperties dokuConfig, Executor<Void, String> sequenceNumberGenerator) {
        return new VaApiServiceAdapter(vaApi, dokuConfig, sequenceNumberGenerator);
    }

    @Bean
    @ConditionalOnMissingBean(VaApiService.class)
    public VaApiService defaultVaApiService() {
        return new DefaultVaApiServiceAdapter();
    }
}