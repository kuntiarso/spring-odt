package com.developer.superuser.virtualaccountservice.vapaymentresource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VaEventListener {
    private final VaHandler vaHandler;

    @KafkaListener(topics = "${kafka.topic.consumer.va-payment-requested}")
    public void onVaPaymentRequested(VaRequestDto vaPaymentRequestedEvent) {
        log.info("Received va payment requested event --- {}", vaPaymentRequestedEvent);
        vaHandler.createVa(vaPaymentRequestedEvent);
    }
}