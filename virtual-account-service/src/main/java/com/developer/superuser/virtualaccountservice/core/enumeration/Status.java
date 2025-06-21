package com.developer.superuser.virtualaccountservice.core.enumeration;

public enum Status {
    PENDING("Pending"),
    PARTIALLY_PAID("Partially Paid"),
    PAID("Paid"),
    OVERPAID("Overpaid"),
    EXPIRED("Expired"),
    FAILED("Failed"),
    CANCELLED("Cancelled"),
    REFUNDED("Refunded"),;

    public final String label;

    Status(String label) {
        this.label = label;
    }
}