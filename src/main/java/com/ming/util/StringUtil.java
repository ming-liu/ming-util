package com.ming.util;

import java.text.SimpleDateFormat;

public class StringUtil {

	public static final String DATEFORMAT_yyyyMMdd = "yyyy-MM-dd";
	public static final String DateRegex = "\\d{4}-(10|11|12|0[1-9])-((0|1|2|3)\\d)";

	/**
	 * yyyy-MM-dd
	 * 
	 * @return
	 */
	public static boolean isDateString(String dateString) {
		try {
			if (dateString == null || dateString.isEmpty())
				return false;
			if (!dateString.matches(DateRegex))
				return false;
			SimpleDateFormat df = new SimpleDateFormat(DATEFORMAT_yyyyMMdd);
			String format = df.format(df.parse(dateString));
			return format.equals(dateString);
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 9; i++) {
			String dateString = "2017-0" + i + "-01";
			System.out.println(dateString + "->" + dateString.matches(DateRegex));
		}
		System.out.println(isDateString("2017-10-01"));
		System.out.println(isDateString("2017-11-01"));
		System.out.println(isDateString("2017-12-01"));
		System.out.println(isDateString("2017-13-01"));
		System.out.println(isDateString("2017-02-29"));
	}
}
