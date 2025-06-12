package com.developer.superuser.tokenservice.core.utility;

import com.developer.superuser.tokenservice.TokenserviceConstant;
import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtility {
    public String getCurrentTimestamp() {
        ZoneId asiaJakartaZone = ZoneId.of(TokenserviceConstant.DATE_TIME_ZONE);
        ZonedDateTime currentDateTime = ZonedDateTime.now(asiaJakartaZone);
        return currentDateTime.format(DateTimeFormatter.ofPattern(TokenserviceConstant.DATE_TIME_FORMAT));
    }
}