package com.ming.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class CompressUtil {

	private static void close(OutputStream os) throws IOException {
		if (os != null) {
			os.close();
		}
	}

	public static byte[] gzip(byte[] bytes) throws IOException {
		byte[] result = null;
		ByteArrayOutputStream out = null;
		GZIPOutputStream gzip = null;
		try {
			out = new ByteArrayOutputStream(bytes.length);
			gzip = new GZIPOutputStream(out);
			gzip.write(bytes);
			gzip.finish();
			close(gzip);
			result = out.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			close(gzip);
			close(out);
		}
		return result;
	}
}
