package com.developer.superuser.virtualaccountservice.vapaymentresource;

import com.developer.superuser.virtualaccountservice.core.property.KafkaTopicConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VaEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTopicConfigProperties kafkaTopicConfigProperties;

    public void publishVaPaymentCreated(String transactionId, VaResponseDto vaPaymentCreatedEvent) {
        log.info("Publishing VaPaymentCreatedEvent with transactionId --- {}", transactionId);
        kafkaTemplate.send(kafkaTopicConfigProperties.getProducer().getVaPaymentCreated(), transactionId, vaPaymentCreatedEvent);
    }
}