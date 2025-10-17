package aishe.gov.in.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * @author NICSI
 */
public abstract class DateUtils {

    static DateTimeFormatter DATE_TIME_FOMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
    static DateTimeFormatter DATE_TIME_FOMATTER_NEW = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
    //static DateTimeFormatter DATE_TIME_FOMATTER_TIME_ZONE_NEW = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss.SSSSSS Z");
    static DateTimeFormatter DATE_TIME_FOMATTER_TIME_ZONE = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss.SSSSSS Z");
    static DateTimeFormatter DATE_FOMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static DateTimeFormatter DATE_FOMATTER_PERSON = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    static DateTimeFormatter TIME_FOMATTER = DateTimeFormatter.ofPattern("hh:mm:ss a");
    static DateTimeFormatter DATE_FOMATTERSEC = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static SimpleDateFormat ddMMyyyy_DATE_FORMATTER = new SimpleDateFormat("ddMMyyyy");


    public static DateTimeFormatter DB_DATE_LOCAL_TIME_PARSE = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .optionalStart()
            .appendFraction(ChronoField.MILLI_OF_SECOND, 1, 3, true)
            .optionalEnd()
            .optionalStart()
            .appendPattern(".")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 1, 3, true)
            .optionalEnd()
            .optionalStart()
            .appendPattern("yyyy-MM-dd")
            .optionalEnd()
            .toFormatter();


    /**
     * @return it will return current time stamp ,this method should be used to get server current time stamp
     */
    public static LocalDateTime obtainCurrentTimeStamp() {
        return LocalDateTime.now();
    }

    /**
     * @return it will return time stamp ,this method should be used to get time stamp from front-end date string
     */
    public static LocalDateTime convertStringDateTimeToDBDateTime(String uiDate) {
        return LocalDateTime.parse(uiDate, DATE_TIME_FOMATTER);

    }

    /**
     * @return it will return string ,this method should be used to get string from db date time stamp
     */
    public static String convertDBDateTimeToString(LocalDateTime dbDate) {
        return DATE_TIME_FOMATTER.format(dbDate);
    }

    /**
     * @return it will return string ,this method should be used to get string as Time Zone Date
     */
    public static String convertDBDateTimeZoneToString() {
        return ZonedDateTime.now().format(DATE_TIME_FOMATTER_TIME_ZONE);

    }

    /**
     * @return it will return current date ,this method should be used to get server current date
     */
    public static LocalDate obtainCurrentDate() {
        return LocalDate.now();
    }

    /**
     * @return it will return date ,this method should be used to get date from front-end date
     */
    public static LocalDate convertStringDateToDBDate(String uiDate) {
        return LocalDate.parse(uiDate, DATE_FOMATTER);
    }

    /**
     * @return it will return string ,this method should be used to get string from db date
     */
    public static String convertDBDateToString(LocalDate dbDate) {
        return DATE_FOMATTER.format(dbDate);
    }

    /**
     * @return it will return current time ,this method should be used to get server current time
     */
    public static LocalTime obtainCurrentTime() {
        return LocalTime.now();
    }

    /**
     * @return it will return time ,this method shou.
     * ld be used to get time from front-end time
     */
    public static LocalTime convertStringTimeToDBTime(String uiTime) {
        return LocalTime.parse(uiTime, TIME_FOMATTER);
    }

    /**
     * @return it will return string ,this method should be used to get string from db time
     */
    public static String convertDBTimeToString(LocalTime dbTime) {
        return TIME_FOMATTER.format(dbTime);
    }

    /**
     * @return it will return date ,this method should be used to get date from front-end date
     */
    public static LocalDate convertStringSlashDateToDBDate(String uiDate) {
        return LocalDate.parse(uiDate, DATE_FOMATTERSEC);
    }

    /**
     * @return it will return string ,this method should be used to get string from db date
     */
    public static String convertDBSlashDateToString(LocalDate dbDate) {
        return DATE_FOMATTERSEC.format(dbDate);
    }

    /**
     * @return it will return date ,this method should be used to get date from front-end date
     */
    public static LocalDate convertStringDateToDBDatePerson(String uiDate) {
        return LocalDate.parse(uiDate, DATE_FOMATTER_PERSON);
    }

    /**
     * @return it will return string ,this method should be used to get string from db date
     */
    public static String convertDBDateToStringPerson(LocalDate dbDate) {
        return DATE_FOMATTER_PERSON.format(dbDate);
    }

    /**
     * @return it will return time stamp ,this method should be used to get time stamp from front-end date string
     */
    public static LocalDateTime convertStringDateTimeToDBDateTimeNew(String uiDate) {
        return LocalDateTime.parse(uiDate, DATE_TIME_FOMATTER_NEW);
    }

    /**
     * @return it will return string ,this method should be used to get string from db date time stamp
     */
    public static String convertDBDateTimeToStringNew(LocalDateTime dbDate) {
        return DATE_TIME_FOMATTER_NEW.format(dbDate);
    }

    public static String convertDBDateTimeToStringNew() {
        LocalDateTime dbDate = LocalDateTime.now();
        return convertDBDateTimeToStringNew(dbDate);
    }

    /**
     * this method will format  current date without WithOutBackslash
     *
     * @return String
     */
    public static String formatDateWithOutBackslash() {
        return ddMMyyyy_DATE_FORMATTER.format(new Date());
    }

    /**
     * this method with return timestamp of current date
     *
     * @return Timestamp
     */
    public static Timestamp getCurrentDateTimestamp() {
        return new Timestamp(new Date().getTime());
    }


    /**
     * this method will format  current date without WithOutBackslash
     *
     * @return String
     */
    public static String formatDateWithOutBackslash(LocalDateTime dateTime) {
        return ddMMyyyy_DATE_FORMATTER.format(dateTime);
    }
}
