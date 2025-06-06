package com.developer.superuser.tokenservice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenserviceConstant {
    public final String SIGNATURE_ENTITY = "\"signature\"";
    public final String FIND_BY_REQUEST_ID = "SELECT * FROM signature s WHERE s.request_id = :requestId";
}