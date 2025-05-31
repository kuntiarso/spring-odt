package com.developer.superuser.tokenservice.core.utility;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@UtilityClass
public class DateUtility {
    public String getCurrentTimestamp() {
        Instant now = Instant.now();
        return DateTimeFormatter.ISO_INSTANT.format(now);
    }
}