package com.developer.superuser.virtualaccountservice.core.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.topic")
@Data
public class KafkaTopicConfigProperties {
    private Consumer consumer;
    private Producer producer;

    @Data
    public static class Consumer {
        private String vaPaymentRequested;
    }

    @Data
    public static class Producer {
        private String vaPaymentCreated;
    }
}