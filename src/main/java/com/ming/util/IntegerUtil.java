package com.ming.util;

import org.apache.log4j.Logger;

public class IntegerUtil {

	private static Logger logger = Logger.getLogger(IntegerUtil.class);

	public static int parseInt(String intString) {
		try {
			return Integer.parseInt(intString);
		} catch (NumberFormatException e) {
			logger.error(e, e);
		}
		return 0;
	}
}
