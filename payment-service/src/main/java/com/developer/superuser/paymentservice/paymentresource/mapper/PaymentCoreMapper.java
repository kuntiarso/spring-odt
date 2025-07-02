package com.developer.superuser.paymentservice.paymentresource.mapper;

import com.developer.superuser.paymentservice.PaymentServiceConstant;
import com.developer.superuser.paymentservice.core.enumeration.PaymentType;
import com.developer.superuser.paymentservice.payment.Payment;
import com.developer.superuser.paymentservice.paymentresource.dto.PaymentVaRequest;
import com.developer.superuser.shared.data.AmountData;
import com.developer.superuser.shared.enumeration.Currency;
import org.springframework.stereotype.Component;

@Component
public class PaymentCoreMapper {
    public Payment toPaymentCore(PaymentVaRequest request) {
        return Payment.builder()
                .setOrderId(request.getOrderId())
                .setUserId(request.getUserId())
                .setType(PaymentType.fromLabel(request.getPaymentType()))
                .setGateway(PaymentServiceConstant.PAYMENT_GATEWAY_DOKU)
                .setAmount(AmountData.builder()
                        .setValue(request.getBilledAmount().getValue())
                        .setCurrency(Currency.valueOf(request.getBilledAmount().getCurrency()))
                        .build())
                .build();
    }
}