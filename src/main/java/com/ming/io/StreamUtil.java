package com.ming.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	public static byte[] readAll2Byte(InputStream is) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[100];
			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				bos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}
}
