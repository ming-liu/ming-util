package com.ming.utl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.ming.util.StringUtil;

public class StringUtilTest {

	@Test
	public void testFormat() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = simpleDateFormat.parse("2017-01-01");
		Calendar instance = Calendar.getInstance();
		instance.setTime(parse);
		for (int i = 0; i < 5000; i++) {
			instance.add(Calendar.DAY_OF_MONTH, 1);
			String format = simpleDateFormat.format(instance.getTime());
			System.out.println((format + StringUtil.isDateString(format)));
		}
	}
}
