package com.developer.superuser.virtualaccountservice.core.config;

import com.developer.superuser.virtualaccountservice.core.property.DokuConfigProperties;
import com.developer.superuser.virtualaccountservice.core.property.KafkaTopicConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({DokuConfigProperties.class, KafkaTopicConfigProperties.class})
public class GlobalConfig {
}