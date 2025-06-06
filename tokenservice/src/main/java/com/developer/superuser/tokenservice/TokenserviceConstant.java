package com.developer.superuser.tokenservice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenserviceConstant {
    public final String SIGNATURE_ENTITY = "\"signature\"";

    public final String FIND_BY_REQUEST_ID = "SELECT * FROM signature s WHERE s.request_id = :requestId";

    public final String KEY_FORMAT_PKCS12 = "PKCS12";

    public static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    public static final String RSA_SHA256_ALGORITHM = "SHA256withRSA";
}