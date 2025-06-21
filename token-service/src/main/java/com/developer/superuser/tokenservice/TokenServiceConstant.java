package com.developer.superuser.tokenservice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenServiceConstant {
    public final String ENTITY_SIGNATURE = "\"signature\"";

    public final String QUERY_FIND_BY_REQUEST_ID = "SELECT * FROM signature s WHERE s.request_id = :requestId";

    public final String DATE_TIME_ZONE = "Asia/Jakarta";
    public final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public final String KEY_FORMAT_PKCS12 = "PKCS12";

    public final String ALGORITHM_HMAC_SHA256 = "HmacSHA256";
    public final String ALGORITHM_HMAC_SHA512 = "HmacSHA512";
    public final String ALGORITHM_RSA_SHA256 = "SHA256withRSA";

    public final String HEADER_SIGNATURE = "X-SIGNATURE";
    public final String HEADER_TIMESTAMP = "X-TIMESTAMP";
    public final String HEADER_CLIENT_KEY = "X-CLIENT-KEY";

    public final String CACHE_TOKEN_B2B = "b2bTokens";
    public final String CACHE_TOKEN_B2B2C = "b2b2cTokens";

    public final String SYMBOL_COLON = ":";
    public final String SYMBOL_NEW_LINE = "\n";

    public final String NON_SNAP_REQUEST_ID = "Request-Id";
    public final String NON_SNAP_CLIENT_ID = "Client-Id";
    public final String NON_SNAP_TIMESTAMP = "Request-Timestamp";
    public final String NON_SNAP_TARGET_ENDPOINT = "Request-Target";
    public final String NON_SNAP_DIGEST = "Digest";
}