package com.developer.superuser.virtualaccountservice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class VirtualAccountServiceConstant {
    public final String ENTITY_VA_PAYMENT_DETAIL = "\"va_payment_detail\"";

    public final String DATE_TIME_ZONE = "Asia/Jakarta";
    public final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public final String HEADER_TIMESTAMP = "X-TIMESTAMP";
    public final String HEADER_SIGNATURE = "X-SIGNATURE";
    public final String HEADER_PARTNER_ID = "X-PARTNER-ID";
    public final String HEADER_EXTERNAL_ID = "X-EXTERNAL-ID";
    public final String HEADER_CHANNEL_ID = "CHANNEL-ID";
    public final String HEADER_AUTHORIZATION = "Authorization";

    public final String VALUE_CHANNEL_ID = "H2H";
}