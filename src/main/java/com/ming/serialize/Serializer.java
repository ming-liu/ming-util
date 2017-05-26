package com.ming.serialize;

import java.io.OutputStream;

public interface Serializer {

	// to byte array
	byte[] serialize(Object o) throws SerializeException;

	// to String
	String serializeToString(Object o) throws SerializeException;

	<T> T deserializeFromString(String value, Class<T> clazz) throws SerializeException;
	
	//
	void serializeRequest(OutputStream os, Object obj) throws SerializeException;

}
