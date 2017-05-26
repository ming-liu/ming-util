package com.ming.serialize.json;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.ming.serialize.SerializeException;
import com.ming.serialize.Serializer;
import com.ming.util.JsonUtil;

public class JsonSerializer implements Serializer {

	@Override
	public byte[] serialize(Object o) {
		String json = serializeToString(o);
		return json.getBytes(Charset.forName("utf-8"));
	}

	@Override
	public void serializeRequest(OutputStream os, Object obj) throws SerializeException {
		try {
			os.write(serialize(obj));
		} catch (IOException e) {
			throw new SerializeException(e.getMessage());
		}
	}

	@Override
	public String serializeToString(Object o) throws SerializeException {
		return JsonUtil.toJson(o);
	}

	@Override
	public <T> T deserializeFromString(String value, Class<T> clazz) throws SerializeException {
		return JsonUtil.fromJson(value, clazz);
	}

}
