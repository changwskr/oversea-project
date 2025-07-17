package com.skcc.oversea.user.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getCurrent(String pattern) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return now.format(formatter);
    }
    
    public static String getCurrentDate() {
        return getCurrent("yyyyMMdd");
    }
    
    public static String getCurrentTime() {
        return getCurrent("HHmmss");
    }
    
    public static String getCurrentDateTime() {
        return getCurrent("yyyyMMddHHmmss");
    }
} 