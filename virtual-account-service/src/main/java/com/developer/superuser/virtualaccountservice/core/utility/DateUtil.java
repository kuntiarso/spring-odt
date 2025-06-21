package com.developer.superuser.virtualaccountservice.core.utility;

import com.developer.superuser.virtualaccountservice.VirtualAccountServiceConstant;
import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {
    public String getCurrentTimestamp() {
        ZoneId asiaJakartaZone = ZoneId.of(VirtualAccountServiceConstant.DATE_TIME_ZONE);
        ZonedDateTime currentDateTime = ZonedDateTime.now(asiaJakartaZone);
        return currentDateTime.format(DateTimeFormatter.ofPattern(VirtualAccountServiceConstant.DATE_TIME_FORMAT));
    }
}