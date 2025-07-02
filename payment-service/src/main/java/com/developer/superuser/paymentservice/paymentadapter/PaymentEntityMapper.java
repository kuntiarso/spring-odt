package com.developer.superuser.paymentservice.paymentadapter;

import com.developer.superuser.paymentservice.payment.Payment;
import com.developer.superuser.shared.data.AmountData;
import com.developer.superuser.shared.embedding.Amount;
import org.springframework.stereotype.Component;

@Component
public class PaymentEntityMapper {
    public PaymentEntity toEntity(Payment payment) {
        return PaymentEntity.builder()
                .setId(payment.getId())
                .setOrderId(payment.getOrderId())
                .setUserId(payment.getUserId())
                .setType(payment.getType())
                .setGateway(payment.getGateway())
                .setAmount(Amount.builder()
                        .setValue(payment.getAmount().getValue())
                        .setCurrency(payment.getAmount().getCurrency())
                        .build())
                .setStatus(payment.getStatus())
                .setErrorCode(payment.getErrorCode())
                .setErrorMessage(payment.getErrorMessage())
                .setPaidAt(payment.getPaidAt())
                .build();
    }

    public Payment toPaymentCore(PaymentEntity entity) {
        return Payment.builder()
                .setId(entity.getId())
                .setOrderId(entity.getOrderId())
                .setUserId(entity.getUserId())
                .setType(entity.getType())
                .setGateway(entity.getGateway())
                .setAmount(AmountData.builder()
                        .setValue(entity.getAmount().getValue())
                        .setCurrency(entity.getAmount().getCurrency())
                        .build())
                .setStatus(entity.getStatus())
                .setErrorCode(entity.getErrorCode())
                .setErrorMessage(entity.getErrorMessage())
                .setPaidAt(entity.getPaidAt())
                .build();
    }
}