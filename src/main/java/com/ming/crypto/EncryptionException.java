package com.ming.crypto;

public class EncryptionException extends RuntimeException {

	private static final long serialVersionUID = -2180057542011955985L;

	public EncryptionException() {
		super();
	}

	public EncryptionException(String msg) {
		super(msg);
	}
}
