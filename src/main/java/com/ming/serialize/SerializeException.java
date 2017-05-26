package com.ming.serialize;

public class SerializeException extends RuntimeException {

	private static final long serialVersionUID = 699270927673970511L;

	public SerializeException() {
		super();
	}

	public SerializeException(String msg) {
		super(msg);
	}

	public SerializeException(Throwable throwable) {
		super(throwable);
	}
}
