package com.zobus.helper;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

public class DurationBetween {

	public static String getDurationBetween(String time1, String time2) {

		return getTimeToString(Timestamp.valueOf(time1), Timestamp.valueOf(time2));
	}

	public static String getDurationBetween(Timestamp time1, Timestamp time2) {

		return getTimeToString(time1, time2);
	}

	private static String getTimeToString(Timestamp time1, Timestamp time2) {

		LocalDateTime dateTime1 = time1.toLocalDateTime();
		LocalDateTime dateTime2 = time2.toLocalDateTime();

		Duration duration = Duration.between(dateTime1, dateTime2);

		// Extract days, hours, minutes, and seconds from the duration
		long days = duration.toDays() * 24;
		long hours = duration.toHours() % 24;
		long minutes = duration.toMinutes() % 60;
//        long seconds = duration.getSeconds() % 60;

		// Format the result
		String result = String.format("%d hrs : %d min", hours + days, minutes);

		return result;
	}
}