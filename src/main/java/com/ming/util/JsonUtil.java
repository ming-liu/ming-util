package com.ming.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	private static Gson gson = null;
	static {
		gson = new GsonBuilder().setDateFormat(DateUtil.DEFAULT).create();
	}

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}
}
