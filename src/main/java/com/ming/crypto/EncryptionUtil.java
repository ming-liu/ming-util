package com.ming.crypto;

import com.ming.crypto.encryptor.NoPaddingEncryptor;

/**
 * AES/CBC/NoPadding
 * 
 * @author liuming
 */
public class EncryptionUtil {

	public static byte[] encrpt(byte[] bytes, byte[] key, byte[] iv) throws EncryptionException {
		return NoPaddingEncryptor.encrpt(bytes, key, iv);
	}
	
	public static byte[] encrpt(byte[] bytes, String key, String iv) throws EncryptionException {
		return NoPaddingEncryptor.encrpt(bytes, key, iv);
	}

	public static byte[] encrpt(String content, String key, String iv) throws EncryptionException {
		return NoPaddingEncryptor.encrpt(content, key, iv);
	}

	public static byte[] decrpt(byte[] content, String key, String iv) throws DecryptionException {
		return NoPaddingEncryptor.decrpt(content, key, iv);
	}

	public static String decrptToStr(byte[] content, String key, String iv) throws DecryptionException {
		return NoPaddingEncryptor.decrptToStr(content, key, iv);
	}

}
