package com.ming.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

import com.ming.crypto.encryptor.NoPaddingEncryptor;

public class CompressUtilTest {

	private static Charset charset = StandardCharsets.UTF_8;

	@Test
	public void testGzipUgzip() throws IOException {
		String str = "hello world,this is a test!! 好不好呢？just have a try..~。";
		byte[] origin = str.getBytes(Charset.forName("utf-8"));

		byte[] gzip = CompressUtil.gzip(origin);
		byte[] unzip = CompressUtil.unGzip(gzip);

		Assert.assertArrayEquals(origin, unzip);
		for (int i = 0; i < unzip.length; i++) {
			System.out.println(unzip[i] == origin[i]);
		}
	}

	/**
	 * first gzip,then encrpt with nopadding.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testGzipUgzipWithNopaddingEncrypt() throws IOException {
		byte[] key = "a376543218dsb000".getBytes(charset);
		byte[] iv = "lleesuctdjeei822".getBytes(charset);
		String str = "testGzipUgzipWithNopaddingEncrypt hello world,this is a test!! 好不好呢？just have a try..~。";
		byte[] origin = str.getBytes(charset);
		
		byte[] p1 = CompressUtil.gzip(origin);
		byte[] p2 = NoPaddingEncryptor.encrpt(p1, key, iv, true);
		
		
		byte[] e1 = NoPaddingEncryptor.decrpt(p2, key, iv, true);
		Assert.assertArrayEquals(p1, e1);
		byte[] e2 = CompressUtil.unGzip(e1);

		Assert.assertArrayEquals(origin, e2);
	}
}
