package com.developer.superuser.paymentservice.core.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ResponseDataMixin<A> {
    @JsonProperty("statusCodeValue")
    private String code;
    @JsonProperty("statusCode")
    private String message;
}