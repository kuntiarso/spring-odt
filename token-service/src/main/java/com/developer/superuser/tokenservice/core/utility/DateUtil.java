package com.developer.superuser.tokenservice.core.utility;

import com.developer.superuser.tokenservice.TokenServiceConstant;
import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {
    public String getCurrentTimestamp() {
        ZoneId asiaJakartaZone = ZoneId.of(TokenServiceConstant.DATE_TIME_ZONE);
        ZonedDateTime currentDateTime = ZonedDateTime.now(asiaJakartaZone);
        return currentDateTime.format(DateTimeFormatter.ofPattern(TokenServiceConstant.DATE_TIME_FORMAT));
    }
}