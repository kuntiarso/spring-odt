package com.developer.superuser.paymentservice.core.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "doku")
@Data
public class DokuConfigProperties {
    private Merchant merchant;

    @Data
    public static class Merchant {
        private String clientId;
    }
}