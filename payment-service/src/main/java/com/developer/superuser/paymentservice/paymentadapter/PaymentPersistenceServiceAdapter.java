package com.developer.superuser.paymentservice.paymentadapter;

import com.developer.superuser.paymentservice.payment.Payment;
import com.developer.superuser.paymentservice.payment.PaymentPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PaymentPersistenceServiceAdapter implements PaymentPersistenceService {
    private final PaymentRepository paymentRepository;
    private final PaymentEntityMapper paymentEntityMapper;

    @Override
    public Payment create(Payment payment) {
        log.info("Persisting payment record");
        PaymentEntity savedPayment = paymentRepository.save(paymentEntityMapper.toEntity(payment));
        log.info("Successfully saved payment record");
        return paymentEntityMapper.toPaymentCore(savedPayment);
    }

    @Override
    public Payment update(Payment payment) {
        log.info("Updating payment record");
        PaymentEntity updatedPayment = paymentRepository.save(paymentEntityMapper.toEntity(payment));
        log.info("Successfully updated payment record");
        return paymentEntityMapper.toPaymentCore(updatedPayment);
    }
}