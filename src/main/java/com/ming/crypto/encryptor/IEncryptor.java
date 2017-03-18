package com.ming.crypto.encryptor;

import com.ming.crypto.EncryptionException;

public interface IEncryptor {

	byte[] encrpt(byte[] bytes, byte[] key, byte[] iv) throws EncryptionException;

	byte[] encrpt(String content, String key, String iv) throws EncryptionException;

}
