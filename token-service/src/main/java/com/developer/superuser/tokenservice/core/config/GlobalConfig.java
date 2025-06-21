package com.developer.superuser.tokenservice.core.config;

import com.developer.superuser.tokenservice.core.property.DokuConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DokuConfigProperties.class)
public class GlobalConfig {
}