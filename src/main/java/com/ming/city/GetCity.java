package com.ming.city;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class GetCity {

	public static final String BASE_URL = "http://m.weather.com.cn/data5/city.xml";
	public static final String CITY_URL = "http://m.weather.com.cn/data5/cityXXXX.xml";

	public static void main(String[] args) throws Exception {
		getBaseCityList("01");
	}

	public static List<CityBean> getBaseCityList(String cityCode) throws Exception {
		List<CityBean> beans = new ArrayList<CityBean>();

		String urlStr = null;
		if (cityCode == null || cityCode.isEmpty()) {
			urlStr = BASE_URL;
		} else {
			urlStr = CITY_URL.replace("XXXX", cityCode);
		}
		URL url = new URL(urlStr);
		URLConnection openConnection = url.openConnection();
		InputStream is = openConnection.getInputStream();
		int read = 0;
		byte[] buffer = new byte[128];
		int count = 0;
		while ((read = is.read()) != -1) {
			if (read == ',') {
				CityBean item = getItem(buffer, count);
				if (item != null) {
					beans.add(item);
				}
				count = 0;
				continue;
			}
			buffer[count++] = (byte) read;
		}
		if (count != 0) {
			CityBean item = getItem(buffer, count);
			if (item != null) {
				beans.add(item);
			}
			count = 0;
		}
		return beans;
	}

	public static List<CityBean> getBaseCityList() throws Exception {
		return getBaseCityList(null);
	}

	private static CityBean getItem(byte[] buffer, int count) {
		String string = new String(buffer, 0, count);
		if (string != null) {
			String[] split = string.split("\\|");
			if (split.length == 2) {
				return new CityBean(split[0], split[1]);
			}
		}
		return null;
	}
}
