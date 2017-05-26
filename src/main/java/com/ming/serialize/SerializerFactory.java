package com.ming.serialize;

import java.util.HashMap;
import java.util.Map;

import com.ming.serialize.java.JavaSerializer;
import com.ming.serialize.json.JsonSerializer;


public class SerializerFactory {

	public static int JAVA_SERIALIZER = 0;
	public static int JSON_SERIALIZER = 1;

	private static Map<Integer, Serializer> serializerMap = new HashMap<Integer, Serializer>();

	static {
		serializerMap.put(JAVA_SERIALIZER, new JavaSerializer());
		serializerMap.put(JSON_SERIALIZER, new JsonSerializer());
	}

	public static Serializer get(int serializer) {
		return serializerMap.get(serializer);
	}
}
