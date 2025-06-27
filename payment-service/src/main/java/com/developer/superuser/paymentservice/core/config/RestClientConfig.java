package com.developer.superuser.paymentservice.core.config;

import com.developer.superuser.paymentservice.core.property.TokenSvcConfigProperties;
import com.developer.superuser.paymentservice.core.property.VaSvcConfigProperties;
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
    public RestClient tokenSvcRestClient(TokenSvcConfigProperties tokenSvcConfig) {
        return buildRestClient(tokenSvcConfig.getBaseUrl(), "TokenSvcRestClient");
    }

    @Bean
    public RestClient vaSvcRestClient(VaSvcConfigProperties vaSvcConfig) {
        return buildRestClient(vaSvcConfig.getBaseUrl(), "VaSvcRestClient");
    }

    private RestClient buildRestClient(String baseUrl, String beanName) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .requestInterceptor(((request, body, execution) -> {
                    log.info("{} request URI --- {}", beanName, request.getURI());
                    log.info("{} request method --- {}", beanName, request.getMethod());
                    log.info("{} request headers --- {}", beanName, request.getHeaders());
                    log.info("{} request body --- {}", beanName, body);
                    return execution.execute(request, body);
                }))
                .build();
    }
}