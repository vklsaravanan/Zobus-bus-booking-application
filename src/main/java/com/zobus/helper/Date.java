package com.zobus.helper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {

	private static int DAY_OF_WEEK;

	public Date(Timestamp date) {

	}

	public Date(Date date) {

	}

	public Date(String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate newDate = LocalDate.parse(date, formatter);

		int dayValue = newDate.getDayOfWeek().getValue();
		DAY_OF_WEEK = dayValue == 7 ? 0 : dayValue;

	}

	public int getDayOfWeek() {
		return DAY_OF_WEEK;
	}

	@Override
	public String toString() {
		return Integer.toString(DAY_OF_WEEK);
	}

}