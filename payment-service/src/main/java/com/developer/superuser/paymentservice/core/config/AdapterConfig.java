package com.developer.superuser.paymentservice.core.config;

import com.developer.superuser.paymentservice.core.helper.OptionalOrElseThrow;
import com.developer.superuser.paymentservice.payment.PaymentPersistenceService;
import com.developer.superuser.paymentservice.paymentadapter.PaymentEntityMapper;
import com.developer.superuser.paymentservice.paymentadapter.PaymentPersistenceServiceAdapter;
import com.developer.superuser.paymentservice.paymentadapter.PaymentRepository;
import com.developer.superuser.paymentservice.tokensvc.TokenSvcApiService;
import com.developer.superuser.paymentservice.tokensvcadapter.TokenSvcApi;
import com.developer.superuser.paymentservice.tokensvcadapter.TokenSvcApiServiceAdapter;
import com.developer.superuser.paymentservice.vasvc.VaSvcApiService;
import com.developer.superuser.paymentservice.vasvcadapter.VaSvcApi;
import com.developer.superuser.paymentservice.vasvcadapter.VaSvcApiServiceAdapter;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {
    @Bean
    public TokenSvcApiService tokenSvcApiService(TokenSvcApi tokenSvcApi, OptionalOrElseThrow<JsonNode> optionalOrElseThrow) {
        return new TokenSvcApiServiceAdapter(tokenSvcApi, optionalOrElseThrow);
    }

    @Bean
    public VaSvcApiService vaSvcApiService(VaSvcApi vaSvcApi, OptionalOrElseThrow<JsonNode> optionalOrElseThrow) {
        return new VaSvcApiServiceAdapter(vaSvcApi, optionalOrElseThrow);
    }

    @Bean
    public PaymentPersistenceService paymentPersistenceService(PaymentRepository paymentRepository, PaymentEntityMapper paymentEntityMapper) {
        return new PaymentPersistenceServiceAdapter(paymentRepository, paymentEntityMapper);
    }
}