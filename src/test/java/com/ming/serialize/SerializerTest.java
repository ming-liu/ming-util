package com.ming.serialize;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SerializerTest {

	@SuppressWarnings("rawtypes")
	@Test
	public void testJson() {
		Map<String, Object> o = new HashMap<String, Object>();
		o.put("id", 123);
		o.put("name", "nnn");
		Serializer serializer = SerializerFactory.get(SerializerFactory.JSON_SERIALIZER);
		String serializeToString = serializer.serializeToString(o);
		System.out.println(serializeToString);
		Object deserializeFromString = serializer.deserializeFromString(serializeToString, Object.class);
		System.out.println(deserializeFromString instanceof Map);
		System.out.println(((Map) deserializeFromString).get("name"));
	}
}
