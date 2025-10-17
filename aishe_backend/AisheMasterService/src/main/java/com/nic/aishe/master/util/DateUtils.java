package com.nic.aishe.master.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author NICSI
 *
 */
public abstract class DateUtils {
	
	static DateTimeFormatter DATE_TIME_FOMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
	static DateTimeFormatter DATE_TIME_FOMATTER_TIME_ZONE = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss.SSSSSS Z");
	static DateTimeFormatter DATE_FOMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	static DateTimeFormatter DATE_FOMATTER_PERSON = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	static DateTimeFormatter TIME_FOMATTER = DateTimeFormatter.ofPattern("hh:mm:ss a");
	static DateTimeFormatter DATE_FOMATTERSEC = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	/**
	 * @return 
	 * it will return current time stamp ,this method should be used to get server current time stamp
	 */
	public static  LocalDateTime obtainCurrentTimeStamp() {
		return LocalDateTime.now();
	}
	/**
	 * @return 
	 * it will return time stamp ,this method should be used to get time stamp from front-end date string
	 */
	public static  LocalDateTime convertStringDateTimeToDBDateTime(String uiDate) {
		return LocalDateTime.parse(uiDate, DATE_TIME_FOMATTER);
		
	}
	
	/**
	 * @return 
	 * it will return string ,this method should be used to get string from db date time stamp
	 */
	public static  String convertDBDateTimeToString(LocalDateTime dbDate) {
		return DATE_TIME_FOMATTER.format(dbDate);
	}
	/**
	 * @return 
	 * it will return string ,this method should be used to get string as Time Zone Date
	 */
	public static  String convertDBDateTimeZoneToString() {
		return ZonedDateTime.now().format(DATE_TIME_FOMATTER_TIME_ZONE);
		
	}
	
	/**
	 * @return 
	 * it will return current date ,this method should be used to get server current date
	 */
	public static  LocalDate obtainCurrentDate() {
		return LocalDate.now();
	}
	
	/**
	 * @return 
	 * it will return date ,this method should be used to get date from front-end date 
	 */
	public static  LocalDate convertStringDateToDBDate(String uiDate) {
		return LocalDate.parse(uiDate, DATE_FOMATTER);
	}
	/**
	 * @return 
	 * it will return string ,this method should be used to get string from db date
	 */
	public static  String convertDBDateToString(LocalDate dbDate) {
		return DATE_FOMATTER.format(dbDate);
	}
	
	/**
	 * @return 
	 * it will return current time ,this method should be used to get server current time
	 */
	public static  LocalTime obtainCurrentTime() {
		return LocalTime.now();
	}
	/**
	 * @return 
	 * it will return time ,this method shou.
	 * ld be used to get time from front-end time 
	 */
	public static  LocalTime convertStringTimeToDBTime(String uiTime) {
		return LocalTime.parse(uiTime, TIME_FOMATTER);
	}
	/**
	 * @return 
	 * it will return string ,this method should be used to get string from db time
	 */
	public static  String convertDBTimeToString(LocalTime dbTime) {
		return TIME_FOMATTER.format(dbTime);
	}

	/**
	 * @return 
	 * it will return date ,this method should be used to get date from front-end date 
	 */
	public static  LocalDate convertStringSlashDateToDBDate(String uiDate) {
		return LocalDate.parse(uiDate, DATE_FOMATTERSEC);
	}
	/**
	 * @return 
	 * it will return string ,this method should be used to get string from db date
	 */
	public static  String convertDBSlashDateToString(LocalDate dbDate) {
		return DATE_FOMATTERSEC.format(dbDate);
	}
	
	/**
	 * @return 
	 * it will return date ,this method should be used to get date from front-end date 
	 */
	public static  LocalDate convertStringDateToDBDatePerson(String uiDate) {
		return LocalDate.parse(uiDate, DATE_FOMATTER_PERSON);
	}
	/**
	 * @return 
	 * it will return string ,this method should be used to get string from db date
	 */
	public static  String convertDBDateToStringPerson(LocalDate dbDate) {
		return DATE_FOMATTER_PERSON.format(dbDate);
	}
}
