package com.developer.superuser.tokenservice.core.enumeration;

public enum GrantType {
    CLIENT_CREDENTIALS("client_credentials"),
    AUTHORIZATION_CODE("authorization_code");

    public final String label;

    GrantType(String label) {
        this.label = label;
    }
}