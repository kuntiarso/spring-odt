package com.developer.superuser.paymentservice.core.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {
    VA("Virtual Account"),
    CC("Credit Card"),
    QRIS("QRIS"),
    E_WALLET("E-Wallet"),
    BANK_TRANSFER("Bank Transfer"),
    CASH("Cash"),
    PAYLATER("Paylater"),
    DEBIT("Debit Card"),
    OTHER("Other");

    private final String label;

    public static PaymentType fromLabel(String label) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.label.equals(label)) {
                return paymentType;
            }
        }
        throw new IllegalArgumentException("Unknown enum label for payment type --- " + label);
    }
}