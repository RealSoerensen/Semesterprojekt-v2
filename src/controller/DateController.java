package controller;

import java.time.LocalDate;
import java.time.Month;

public class DateController {

	public static LocalDate getLocalDate(int[] intDate) {
		LocalDate date = null;
		int year = intDate[0];
		int month = intDate[1];
		int day = intDate[2];

		if (isValidMonth(month)) {
			date = LocalDate.of(year, Month.of(month), day);
		}

		return date;
	}

	private static boolean isValidMonth(int month) {
		return month >= 1 && month <= 12;
	}
}
