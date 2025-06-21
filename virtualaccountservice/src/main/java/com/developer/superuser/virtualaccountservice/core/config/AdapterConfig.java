package com.developer.superuser.virtualaccountservice.core.config;

import com.developer.superuser.virtualaccountservice.vapayment.DefaultVaPersistenceServiceAdapter;
import com.developer.superuser.virtualaccountservice.vapayment.VaPersistenceService;
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
}