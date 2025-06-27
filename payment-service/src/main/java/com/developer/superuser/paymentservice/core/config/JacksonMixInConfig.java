package com.developer.superuser.paymentservice.core.config;

import com.developer.superuser.paymentservice.core.data.ResponseDataMixin;
import com.developer.superuser.shared.data.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonMixInConfig {
    public JacksonMixInConfig(ObjectMapper objectMapper) {
        objectMapper.addMixIn(ResponseData.class, ResponseDataMixin.class);
    }
}