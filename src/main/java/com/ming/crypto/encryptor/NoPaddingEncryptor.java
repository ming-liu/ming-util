package com.ming.crypto.encryptor;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.ming.crypto.DecryptionException;
import com.ming.crypto.EncryptionException;

/**
 * 
 * 对称加密 AES加密模式和填充方式,填充方式3中NoPadding,PKCS5Padding,ISO10126Padding

算法/模式/填充                16字节加密后数据长度        不满16字节加密后长度
AES/CBC/NoPadding             16                          不支持
AES/CBC/PKCS5Padding          32                          16
AES/CBC/ISO10126Padding       32                          16
AES/CFB/NoPadding             16                          原始数据长度
AES/CFB/PKCS5Padding          32                          16
AES/CFB/ISO10126Padding       32                          16
AES/ECB/NoPadding             16                          不支持
AES/ECB/PKCS5Padding          32                          16
AES/ECB/ISO10126Padding       32                          16
AES/OFB/NoPadding             16                          原始数据长度
AES/OFB/PKCS5Padding          32                          16
AES/OFB/ISO10126Padding       32                          16
AES/PCBC/NoPadding            16                          不支持
AES/PCBC/PKCS5Padding         32                          16
AES/PCBC/ISO10126Padding      32                          16


NoPadding填充方式,处理字符串比较方便。不足16字节时，补0，一般字符串不会有\0的。
如果拿来处理其他一些流，比如gzip(以\0\0)结尾，这个时候，不好解密出来。
其他填充方式加密后长度double了

 * @author liuming
 *
 */
public class NoPaddingEncryptor {

	private static final String ENCODING_TEXT = "UTF-8";

	private static final String AES_Transform = "AES/CBC/NoPadding";
	private static final String AES_Algrithm = "AES";

	
	
	public static byte[] encrpt(byte[] bytes, byte[] key, byte[] iv) throws EncryptionException {
		try {
			Cipher cipher = Cipher.getInstance(AES_Transform);
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, AES_Algrithm), new IvParameterSpec(iv));
			return cipher.doFinal(padding(bytes));
		} catch (Exception e) {
			throw new EncryptionException(e.getMessage());
		}
	}
	
	public static byte[] encrpt(byte[] bytes, String key, String iv) throws EncryptionException {
		try {
			return encrpt(bytes, key.getBytes(ENCODING_TEXT), iv.getBytes(ENCODING_TEXT));
		} catch (UnsupportedEncodingException e) {
			throw new EncryptionException(e.getMessage());
		}
	}

	public static byte[] encrpt(String content, String key, String iv) throws EncryptionException {
		try {
			return encrpt(content.getBytes(ENCODING_TEXT), key.getBytes(ENCODING_TEXT), iv.getBytes(ENCODING_TEXT));
		} catch (UnsupportedEncodingException e) {
			throw new EncryptionException(e.getMessage());
		}
	}

	private static byte[] strip(byte[] bytes) {
		int blank = 0;
		for (int i = bytes.length - 1; i >= 0; i--) {
			if (bytes[i] == '\0') {
				blank++;
			} else {
				break;
			}
		}
		byte[] result = new byte[bytes.length - blank];
		System.arraycopy(bytes, 0, result, 0, result.length);
		return result;
	}

	public static byte[] decrpt(byte[] content, String key, String iv) throws DecryptionException {
		try {
			Cipher cipher = Cipher.getInstance(AES_Transform);
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(ENCODING_TEXT), AES_Algrithm), new IvParameterSpec(iv.getBytes(ENCODING_TEXT)));
			byte[] withPadding = cipher.doFinal(content);
			byte[] result = strip(withPadding);
			return result;
		} catch (Exception e) {
			throw new DecryptionException(e.getMessage());
		}
	}

	public static String decrptToStr(byte[] content, String key, String iv) throws DecryptionException {
		try {
			byte[] bytes = decrpt(content, key, iv);
			return new String(bytes, ENCODING_TEXT);
		} catch (Exception e) {
			throw new DecryptionException(e.getMessage());
		}
	}

	public static byte[] padding(byte[] bytes) {
		int mod = bytes.length % 16;
		if (mod > 0) {
			byte[] result = new byte[bytes.length + (16 - mod)];
			System.arraycopy(bytes, 0, result, 0, bytes.length);
			return result;
		}
		return bytes;
	}
	
	/**
	 * @param bytes
	 * @param key
	 * @param iv
	 * @param strict
	 *            如果原始数组里可能有\0结尾,此参数需要传true。一般utf8字符串不需要此参数。解密时需要配套使用。
	 * @return
	 * @throws EncryptionException
	 */
	public static byte[] encrpt(byte[] bytes, byte[] key, byte[] iv, boolean strict) throws EncryptionException {
		try {
			Cipher cipher = Cipher.getInstance(AES_Transform);
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, AES_Algrithm), new IvParameterSpec(iv));
			return cipher.doFinal(strict ? strictPadding(bytes) : padding(bytes));
		} catch (Exception e) {
			throw new EncryptionException(e.getMessage());
		}
	}
	
	public static byte[] decrpt(byte[] content, byte[] key, byte[] iv) throws DecryptionException {
		return decrpt(content, key, iv, false);
	}

	public static byte[] decrpt(byte[] content, byte[] key, byte[] iv, boolean strict) throws DecryptionException {
		try {
			Cipher cipher = Cipher.getInstance(AES_Transform);
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, AES_Algrithm), new IvParameterSpec(iv));
			byte[] withPadding = cipher.doFinal(content);
			byte[] result = strict ? strictStrip(withPadding) : strip(withPadding);
			return result;
		} catch (Exception e) {
			throw new DecryptionException(e.getMessage());
		}
	}

	public static byte[] strictPadding(byte[] bytes) {
		int mod = bytes.length % 16;
		byte[] result = new byte[bytes.length + (mod == 0 ? 0 : (16 - mod)) + 16];
		System.arraycopy(bytes, 0, result, 0, bytes.length);
		for (int i = 0; i < 16; i++) {
			result[result.length - 1 - i] = (mod == 0) ? 0 : (byte) (16 - mod);
		}
		return result;
	}

	private static byte[] strictStrip(byte[] bytes) {
		int paddingCount = bytes[bytes.length - 1];
		byte[] result = new byte[bytes.length - 16 - paddingCount];
		System.arraycopy(bytes, 0, result, 0, result.length);
		return result;
	}
	
}
