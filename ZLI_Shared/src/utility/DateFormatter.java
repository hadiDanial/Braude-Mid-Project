package utility;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateFormatter
{
	 private static final String DATE_PATTERN_FORMAT = "dd.MM.yyyy";
	 private static final String DATE_TIME_PATTERN_FORMAT = "HH:mm dd.MM.yyyy";

	    public static String formatInstant(Instant time, boolean includeHour) 
	    {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(includeHour ? DATE_TIME_PATTERN_FORMAT : DATE_PATTERN_FORMAT)
	            .withZone(ZoneId.systemDefault());
	        return formatter.format(time);
	    }
}
