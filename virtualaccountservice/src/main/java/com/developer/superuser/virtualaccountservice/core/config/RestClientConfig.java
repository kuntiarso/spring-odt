package com.developer.superuser.virtualaccountservice.core.config;

import com.developer.superuser.virtualaccountservice.core.property.DokuConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
@Slf4j
public class RestClientConfig {
    @Bean
    public RestClient dokuRestClient(DokuConfigProperties dokuConfig) {
        return RestClient.builder()
                .baseUrl(dokuConfig.getApi().getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .requestInterceptor(((request, body, execution) -> {
                    log.info("DokuRestClient request URI --- {}", request.getURI());
                    log.info("DokuRestClient request method --- {}", request.getMethod());
                    log.info("DokuRestClient request headers --- {}", request.getHeaders());
                    log.info("DokuRestClient request body --- {}", body);
                    return execution.execute(request, body);
                }))
                .build();
    }
}