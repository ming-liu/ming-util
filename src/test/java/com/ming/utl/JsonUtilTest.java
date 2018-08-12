package com.ming.utl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ming.util.JsonUtil;

public class JsonUtilTest {

	@Test
	public void testToJson() throws ParseException {
		Map<String,Object> c = new HashMap<String,Object>();
		c.put("a", "hl");
		c.put("b", 123);
		
		Map<String,Object> d = new HashMap<String,Object>();
		d.put("d1", "111");
		d.put("d2", 123);
		c.put("d", d);
		System.out.println(JsonUtil.toJson(c));
	}
}
