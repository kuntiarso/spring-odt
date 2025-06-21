package com.developer.superuser.virtualaccountservice.core.enumeration;

public enum TransactionType {
    C("Closed Amount"),
    O("Open Amount"),;

    public final String label;

    TransactionType(String label) {
        this.label = label;
    }
}