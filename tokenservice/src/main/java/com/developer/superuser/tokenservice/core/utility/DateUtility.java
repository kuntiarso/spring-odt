package com.developer.superuser.tokenservice.core.utility;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@UtilityClass
public class DateUtility {
    public String getCurrentTimestamp() {
        Instant now = Instant.now();
        Instant nowWithoutMillis = now.truncatedTo(ChronoUnit.SECONDS);
        return DateTimeFormatter.ISO_INSTANT.format(nowWithoutMillis);
    }
}