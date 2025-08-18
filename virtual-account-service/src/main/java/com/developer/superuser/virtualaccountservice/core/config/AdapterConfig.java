package com.developer.superuser.virtualaccountservice.core.config;

import com.developer.superuser.shared.project.springodt.sign.Symmetric;
import com.developer.superuser.virtualaccountservice.vapayment.VaApiService;
import com.developer.superuser.virtualaccountservice.vapayment.VaPersistenceService;
import com.developer.superuser.virtualaccountservice.vapaymentadapter.api.VaApi;
import com.developer.superuser.virtualaccountservice.vapaymentadapter.api.VaApiServiceAdapter;
import com.developer.superuser.virtualaccountservice.vapaymentadapter.db.VaPaymentDetailEntityMapper;
import com.developer.superuser.virtualaccountservice.vapaymentadapter.db.VaPersistenceServiceAdapter;
import com.developer.superuser.virtualaccountservice.vapaymentadapter.db.VaRepository;
import com.developer.superuser.virtualaccountservice.vapaymentresource.mapper.SignMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {
    @Bean
    public VaPersistenceService vaPersistenceService(VaRepository vaRepository, VaPaymentDetailEntityMapper vaPaymentDetailEntityMapper) {
        return new VaPersistenceServiceAdapter(vaRepository, vaPaymentDetailEntityMapper);
    }

    @Bean
    public VaApiService vaApiService(Symmetric symmetric, SignMapper signMapper, VaApi vaApi) {
        return new VaApiServiceAdapter(symmetric, signMapper, vaApi);
    }
}