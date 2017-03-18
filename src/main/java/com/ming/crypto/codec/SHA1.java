package com.ming.crypto.codec;

import java.security.MessageDigest;

public class SHA1 {

	public static final String SHA1 = "SHA-1";

	public static String digest(String key) {
		try {
			MessageDigest md = MessageDigest.getInstance(SHA1);
			byte[] digest = md.digest(key.getBytes("UTF-8"));
			String mdKey = HexString.convertByte2Hex(digest);
			return mdKey;
		} catch (Exception e) {
		}
		return null;
	}
}
