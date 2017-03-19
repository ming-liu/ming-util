package com.ming.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.ming.crypto.EncryptionUtil;

public class CompressUtil {

	private static void close(OutputStream os) throws IOException {
		if (os != null) {
			os.close();
		}
	}

	private static void close(InputStream is) throws IOException {
		if (is != null) {
			is.close();
		}
	}

	public static byte[] unGzip(byte[] bytes) throws IOException {
		GZIPInputStream gzip = null;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream(bytes.length * 2);
			byte[] buffer = new byte[512];
			gzip = new GZIPInputStream(new ByteArrayInputStream(bytes));
			int length = 0;
			while ((length = gzip.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			return out.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			close(gzip);
		}
	}

	public static byte[] gzip(byte[] bytes) throws IOException {
		GZIPOutputStream gzip = null;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream(bytes.length);
			gzip = new GZIPOutputStream(out);
			gzip.write(bytes);
			gzip.finish();
			close(gzip);
			return out.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			close(gzip);
		}
	}

	public static void main(String[] args) throws Throwable {
		String str = "hello world";
		byte[] encrpt = EncryptionUtil.encrpt("str", "1234567890123456", "1234567890123456");

		byte[] gzip = gzip(encrpt);
		System.out.println(gzip);
		byte[] encrpt2 = unGzip(gzip);

	}
}
