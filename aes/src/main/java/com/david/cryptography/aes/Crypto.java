package com.david.cryptography.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;

public class Crypto {

	private String password;
	
	public Crypto(String password) {
		super();
		this.password = password;
	}

	public String encrypt(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes(Charsets.US_ASCII));
		byte[] rawKey = md.digest();
		byte[] hash = new byte[32];
		System.arraycopy(rawKey, 0, hash, 0, 16);
		//In the original algorithm it's a mistake so I keep it too. It overwrites 15 position of the array
		System.arraycopy(rawKey, 0, hash, 15, 16);

		SecretKeySpec skeySpec = new SecretKeySpec(hash, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

		byte[] encrypted = cipher.doFinal(input.getBytes(Charsets.US_ASCII));
		return new String(Base64.encodeBase64(encrypted));
	}
	
	public String decrypt(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes(Charsets.US_ASCII));
		byte[] rawKey = md.digest();
		byte[] hash = new byte[32];
		System.arraycopy(rawKey, 0, hash, 0, 16);
		//In the original algorithm it's a mistake so I keep it too. It overwrites 15 position of the array
		System.arraycopy(rawKey, 0, hash, 15, 16);
		SecretKeySpec skeySpec = new SecretKeySpec(hash, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);

		byte[] encrypted = cipher.doFinal(Base64.decodeBase64(input));
		return new String(encrypted,Charsets.US_ASCII);
	}
}
