package com.ming.crypto;

import com.ming.crypto.codec.HexString;

public class TokenUtil {

	private static final String key = "07C1F71A22153AE5";
	private static final String iv = "55F930A817BDABFD";

	/**
	 * @param content
	 * @param key
	 *            16length
	 * @param iv
	 *            16length
	 * @return
	 */
	public static String generateToken(String content, String key, String iv) {
		byte[] encrpt = EncryptionUtil.encrpt(content, key, iv);
		return HexString.convertByte2Hex(encrpt);
	}

	public static String generateToken(String content) {
		byte[] encrpt = EncryptionUtil.encrpt(content, key, iv);
		return HexString.convertByte2Hex(encrpt);
	}

	public static String parseToken(String token) {
		byte[] bytes = HexString.converHexStr2Bytes(token);
		return EncryptionUtil.decrptToStr(bytes, key, iv);
	}
	
	public static String parseToken(String token, String key, String iv) {
		byte[] bytes = HexString.converHexStr2Bytes(token);
		return EncryptionUtil.decrptToStr(bytes, key, iv);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(parseToken(generateToken("1|234567890|_|abcdefghijklmnopqrstuvwxyz-ABCDEFGHIJKLMNOPQRSTUVWXYZ")));
	}
}
