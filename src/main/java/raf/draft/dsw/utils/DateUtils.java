package raf.draft.dsw.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {
    private DateUtils() { }

    private static final String LoggingDateFormat = "yyyy-MM-dd HH:mm:ss";

    public static String getDateForLogging(){ return LocalDateTime.now().format(DateTimeFormatter.ofPattern(LoggingDateFormat)); }
}
