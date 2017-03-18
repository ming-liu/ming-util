package com.ming.crypto.codec;

import java.security.MessageDigest;

// replace with org.apache.commons.codec.digest.DigestUtils.md5Hex(String)
public class MD5 {

	public static final String MD5 = "MD5";

	public static String digest(String key) {
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			byte[] digest = md.digest(key.getBytes("UTF-8"));
			String mdKey = HexString.convertByte2Hex(digest);
			return mdKey;
		} catch (Exception e) {
		}
		return null;
	}
}
