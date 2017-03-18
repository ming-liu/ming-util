package com.ming.serialize;

import java.io.OutputStream;

public interface Serializer {

	byte[] serialize(Object o);

	void serializeRequest(OutputStream os, Object obj) throws SerializationException;

}
