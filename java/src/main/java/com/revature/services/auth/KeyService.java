package com.revature.services.auth;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.revature.util.LogInterface;

public class KeyService {

	private static byte[] encodedKey;
//	private static String EncryptionKey = "EncryptionKey";
//
//	public KeyService() {
//		String keyStr = PropertiesManager.get(EncryptionKey);
//		if (keyStr != null && !keyStr.isEmpty()) {
//			encodedKey = keyStr.getBytes();
//		} else {
////			generate 256-bit AES key
//			KeyGenerator generator;
//			try {
//				generator = KeyGenerator.getInstance("AES");
//				generator.init(256);
//				SecretKey key = generator.generateKey();
//				// get the key in encoded format
//				encodedKey = key.getEncoded();
//				PropertiesManager.set(EncryptionKey, new String(encodedKey));
//			} catch (NoSuchAlgorithmException e) {
//				LogInterface.logStackTrace(e);
//			}
//		}
//	}

	public KeyService() {
		genKey();
	}

	private byte[] genKey() {
		if (encodedKey != null) {
			return encodedKey;
		}
//		generate 256-bit AES key
		KeyGenerator generator;
		try {
			generator = KeyGenerator.getInstance("AES");
			generator.init(256);
			SecretKey key = generator.generateKey();
			// get the key in encoded format
			return encodedKey = key.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			LogInterface.logStackTrace(e);
		}
		return null;

	}

	public byte[] getEncodedKey() {
		return genKey();
	}

}
