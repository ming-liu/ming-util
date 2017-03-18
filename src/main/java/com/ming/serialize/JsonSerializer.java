package com.ming.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.google.gson.Gson;

public class JsonSerializer implements Serializer {

	@Override
	public byte[] serialize(Object o) {
		String json = new Gson().toJson(o);
		byte[] bytes = json.getBytes(Charset.forName("utf-8"));
		return bytes;
	}

	@Override
	public void serializeRequest(OutputStream os, Object obj) throws SerializationException {
		try {
			os.write(serialize(obj));
		} catch (IOException e) {
			throw new SerializationException(e.getMessage());
		}
	}

}
