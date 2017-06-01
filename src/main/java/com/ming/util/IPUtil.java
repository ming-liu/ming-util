package com.ming.util;

public class IPUtil {

	public static long toInt(String ip) {
		int offset = 0;
		if (ip.endsWith(".")) {
			offset = 1;
		}
		String[] split = ip.split("\\.");
		long ipValue = 0;
		for (int i = 0; i < split.length - offset; i++) {
			ipValue = (ipValue << 8) + Integer.parseInt(split[i]);
		}
		return ipValue;
	}

	public static String toString(long ipValue) {
		long f1 = (ipValue >> 24);
		long f2 = (ipValue - (f1 << 24)) >> 16;
		long f3 = (ipValue - (f1 << 24) - (f2 << 16)) >> 8;
		long f4 = (ipValue - (f1 << 24) - (f2 << 16) - (f3 << 8));
		return f1 + "." + f2 + "." + f3 + "." + f4;
	}

	public static void main(String[] args) {
		long int1 = toInt("192.133.22.2");
		System.out.println(int1);
		System.out.println(toString(int1));
		
		String string = toString(3229970266l);
		System.out.println(string);
		System.out.println(toInt(string));
	}
}
