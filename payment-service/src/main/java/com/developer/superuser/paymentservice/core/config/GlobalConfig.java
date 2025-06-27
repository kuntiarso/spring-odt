package com.developer.superuser.paymentservice.core.config;

import com.developer.superuser.paymentservice.core.property.TokenSvcConfigProperties;
import com.developer.superuser.paymentservice.core.property.VaSvcConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({TokenSvcConfigProperties.class, VaSvcConfigProperties.class})
public class GlobalConfig {
}