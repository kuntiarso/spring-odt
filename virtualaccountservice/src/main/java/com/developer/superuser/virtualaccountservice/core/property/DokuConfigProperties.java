package com.developer.superuser.virtualaccountservice.core.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "doku")
@Data
public class DokuConfigProperties {
    private Api api;

    @Data
    public static class Api {
        private String key;
        private String baseUrl;
        private Map<String, String> endpoint;
    }
}