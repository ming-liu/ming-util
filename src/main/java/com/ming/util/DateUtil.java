package com.ming.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public final static String DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public final static String DEFAULT_DATE = "yyyy-MM-dd";

	public final static String PATTERN_MIN = "yyyy-MM-dd HH:mm";

	public static Date parse(String str, String pattern) {
		try {
			if (str != null && str.length() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				return sdf.parse(str);
			}
		} catch (ParseException e) {
		}
		return null;
	}

	public static Date parse(String str) {
		return parse(str, DEFAULT);
	}

}
