package com.ming.crypto.codec;

public class HexString {

	private static final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String convertByte2Hex(byte[] encrpt) {
		int l = encrpt.length;
		char[] out = new char[l << 1];

		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = CHARS[(encrpt[i] & 0xf0) >>> 4];
			out[j++] = CHARS[encrpt[i] & 0x0f];
		}

		return new String(out);
	}

	public static byte[] converHexStr2Bytes(String token) {
		char[] charArray = token.toCharArray();
		int length = charArray.length / 2;
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			bytes[i] = (byte) (char2Byte(charArray[i * 2]) << 4 | char2Byte(charArray[i * 2 + 1]));
		}
		return bytes;
	}

	private static byte char2Byte(char c) {
		if (c >= '0' && c <= '9') {
			return (byte) (c - '0');
		} else {
			return (byte) (c - 'a' + 10);
		}
	}
}
