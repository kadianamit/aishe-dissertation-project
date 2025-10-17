package aishe.gov.in.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalculateTime {

	public static String getCurrentLocalDateTimeStamp() {
	    return LocalDateTime.now()
	       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}
}
