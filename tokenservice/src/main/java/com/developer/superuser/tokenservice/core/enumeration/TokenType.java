package com.developer.superuser.tokenservice.core.enumeration;

public enum TokenType {
    B2B("b2b"),
    B2B2C("b2b2c");

    public final String label;

    TokenType(String label) {
        this.label = label;
    }
}