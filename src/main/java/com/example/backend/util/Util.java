package com.example.backend.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Util {

    // ISO 8601 BASIC is used by the API signature
    public static String ISO_8601BASIC_DATE_PATTERN = "yyyyMMdd'T'HHmmss'Z'";

    public static boolean isIsoTimestamp(String s) {

        return s.matches("\\d{8}T\\d{6}Z");
    }
    public static String parseIsoDateTime(String s)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_8601BASIC_DATE_PATTERN);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(cal.getTime());
    }
}
