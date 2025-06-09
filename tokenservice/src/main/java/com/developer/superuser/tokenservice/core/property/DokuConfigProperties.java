package com.developer.superuser.tokenservice.core.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "doku.api")
@Data
public class DokuConfigProperties {
    private String secretKey;
    private String baseUrl;
    private Merchant merchant;
    private Endpoint endpoint;

    @Data
    public static class Merchant {
        private KeyStore keyStore;
        private PrivateKey privateKey;
    }

    @Data
    public static class KeyStore {
        private String location;
        private String alias;
        private String password;
    }

    @Data
    public static class PrivateKey {
        private String passphrase;
    }

    @Data
    public static class Endpoint {
        private String accessToken;
    }
}