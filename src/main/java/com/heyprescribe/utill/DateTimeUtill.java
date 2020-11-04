package com.heyprescribe.utill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtill {
	public static String value;
	public static String todaysDate;
	public static String WeekMidDate;
	public static String WeekLastDate;

	public static void currentDate() {
		DateFormat dform = new SimpleDateFormat("dd");
		Date obj = new Date();
		value = dform.format(obj);
	}

	public static void dateMonthYear() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		todaysDate = dtf.format(LocalDateTime.now());
		WeekMidDate = dtf.format(LocalDateTime.now().plusDays(2));
		WeekLastDate = dtf.format(LocalDateTime.now().plusDays(6));
	}

	public static String date(String dateMonthYear) {
		String parts[] = dateMonthYear.split("-");
		String date = parts[0];
		if (Integer.parseInt(date) < 10) {
			return Character.toString(date.charAt(1));
		} else {
			return date;
		}
	}

}
