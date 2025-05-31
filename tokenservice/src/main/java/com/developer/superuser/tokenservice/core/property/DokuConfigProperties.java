package com.developer.superuser.tokenservice.core.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "doku.api")
@Data
public class DokuConfigProperties {
    private String secretKey;
}