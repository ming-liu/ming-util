package com.ming.crypto;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

import com.ming.crypto.encryptor.NoPaddingEncryptor;

public class EncryptionUtilTest {

	private static Charset charset = StandardCharsets.UTF_8;

	@Test
	public void testNopaddingEncryptAndDecrypt() throws IOException {
		byte[] key = "a376543218dsb000".getBytes(charset);
		byte[] iv = "lleesuctdjeei822".getBytes(charset);
		String str = "testGzipUgzipWithNopaddingEncrypt hello world,this is a test!! 好不好呢？just have a try..~。";
		byte[] origin = str.getBytes(Charset.forName("utf-8"));

		byte[] encrypt = NoPaddingEncryptor.encrpt(origin, key, iv);
		byte[] decrypt = NoPaddingEncryptor.decrpt(encrypt, key, iv);
		Assert.assertArrayEquals(origin, decrypt);

		encrypt = NoPaddingEncryptor.encrpt(origin, key, iv, true);
		decrypt = NoPaddingEncryptor.decrpt(encrypt, key, iv, true);
		Assert.assertArrayEquals(origin, decrypt);
	}

	/**
	 * @throws IOException
	 */
	@Test
	public void testNopaddingEncryptWithPadding() throws IOException {
		String key = "a376543218dsb000";
		String iv = "lleesuctdjeei822";
		String str = "testGzipUgzipWithNopaddingEncrypt hello world,this is a test!! 好不好呢？just have a try..~。";
		byte[] origin = str.getBytes(Charset.forName("utf-8"));
		byte[] paddingOrigin = new byte[origin.length + 1];
		System.arraycopy(origin, 0, paddingOrigin, 0, origin.length);

		byte[] encrypt = NoPaddingEncryptor.encrpt(paddingOrigin, key, iv);

		byte[] decrypt = NoPaddingEncryptor.decrpt(encrypt, key, iv);
		Assert.assertTrue(paddingOrigin.length == decrypt.length + 1);
	}

	@Test
	public void testNopaddingEncrypt() throws IOException {
		String key = "a376543218dsb000";
		String iv = "lleesuctdjeei822";
		String str = "a376543218dsb000";
		byte[] origin = str.getBytes(Charset.forName("utf-8"));
		byte[] encrypt = EncryptionUtil.encrpt(origin, key, iv);
		System.out.println(encrypt.length);
	}

}
