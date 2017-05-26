package com.ming.serialize.java;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.ming.crypto.codec.HexString;
import com.ming.serialize.SerializeException;
import com.ming.serialize.Serializer;

public class JavaSerializer implements Serializer {

	@Override
	public byte[] serialize(Object o) throws SerializeException {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(bos);
				oos.writeObject(o);
				oos.flush();
			} finally {
				if (oos != null)
					oos.close();
			}
			return bos.toByteArray();
		} catch (Throwable e) {
			throw new SerializeException(e);
		}
	}

	@Override
	public void serializeRequest(OutputStream os, Object obj) throws SerializeException {

	}

	@Override
	public String serializeToString(Object o) throws SerializeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T deserializeFromString(String value, Class<T> clazz) throws SerializeException {
		byte[] bytes = HexString.converHexStr2Bytes(value);
		return null;
	}

	// @Override
	// public Object deserialize(String input) {
	// byte[] bytes = HexString.converHexStr2Bytes(input);
	// ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	// ObjectInputStream objectInputStream = null;
	// Object readObject = null;
	// try {
	// try {
	// objectInputStream = new ObjectInputStream(bais);
	// readObject = objectInputStream.readObject();
	// } finally {
	// objectInputStream.close();
	// }
	// } catch (Throwable e) {
	// throw new SerializeException(e);
	// }
	// return readObject;
	// }

	// @SuppressWarnings("unchecked")
	// @Override
	// public <T> T deserialize(String input, Class<T> clazz) throws
	// SerializeException {
	// Object deserialize = deserialize(input);
	// T result = null;
	// try {
	// result = (T) deserialize;
	// } catch (Exception e) {
	// throw new SerializeException(e);
	// }
	// return result;
	// }

}
