package com.developer.superuser.tokenservice.core.helper;

public interface GenericHelper<A, B> {
    B execute(A a);
}