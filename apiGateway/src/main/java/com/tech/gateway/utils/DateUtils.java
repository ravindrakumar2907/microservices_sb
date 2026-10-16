package com.tech.gateway.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static final String DATE_FORMAT = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
	public static final String DATE_FORMAT1 = "dd-MM-yyyy HH:mm:ss.SSSZ";
	public static final String UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String IST_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSX";

	public static Date getDate() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT1, new Locale("IN"));
		return simpleDateFormat.parse(new Date().toString());
	}

	public static Date getDate(String format, Date date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, new Locale("IN"));
		return simpleDateFormat.parse(date.toString());
	}

	public static String getDateInString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UTC_FORMAT, new Locale("IN"));
		return simpleDateFormat.format(new Date());
	}

	public static String getDateInString(String format, Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, new Locale("IN"));
		return simpleDateFormat.format(date);
	}
	
	//https://www.baeldung.com/java-add-hours-date
	public static Date addHoursToJavaUtilDate(Date date, int hours) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, hours);
	    return calendar.getTime();
	}
	
	public static String getDate1() {
        // Get the current UTC time
        Instant utcTime = Instant.now();

        // Convert UTC to IST
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        ZonedDateTime istDateTime = ZonedDateTime.ofInstant(utcTime, istZone);

        // Format the IST date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String istString = istDateTime.format(formatter);

        System.out.println("IST Time: " + istString);
        return istString;
    }

}

/*
 * 
 * https://www.digitalocean.com/community/tutorials/java-simpledateformat-java-date-format
 * https://saturncloud.io/blog/conversion-of-utc-to-ist-time-in-java-working-locally-but-not-on-cloud-server/
 *https://stackoverflow.com/questions/5105044/convert-gmt-to-ist-in-java
 *
	
 */
