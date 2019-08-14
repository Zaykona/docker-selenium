package utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public final static String getCurrentDateAsString(String timeZone) {
       return getCurrentDateAsString("MM/dd/yyyy, HH:mm", timeZone);
    }

    public final static String getCurrentDateAsString(String pattern, String timeZone) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now(ZoneId.of(timeZone));
        return dtf.format(now);

    }

}
