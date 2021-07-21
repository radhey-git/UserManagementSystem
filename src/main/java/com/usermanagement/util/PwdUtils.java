package com.usermanagement.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.usermanagement.exception.PasswordEncryptDecryptException;

public class PwdUtils {
	private static final String SECRET_KEY = "abc123xyz123abcd";
	private static final String INIT_VECTOR = "abc123xyz123abcd";

	public static String encryptPwd(String pazzword) throws PasswordEncryptDecryptException {
		byte[] encrypted = null;
		try {
			IvParameterSpec ivParamSpec = new IvParameterSpec(INIT_VECTOR.getBytes());

			SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParamSpec);

			encrypted = cipher.doFinal(pazzword.getBytes());
		} catch (Exception e) {
			throw new PasswordEncryptDecryptException(e.getMessage());
		}

		return Base64.getEncoder().encodeToString(encrypted);
	}

	public static String decryptPwd(String pazzword) throws PasswordEncryptDecryptException {
		byte[] decrypted = null;
		try {
			IvParameterSpec ivParamSpec = new IvParameterSpec(INIT_VECTOR.getBytes());

			SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParamSpec);

			byte[] decodedMsg = Base64.getDecoder().decode(pazzword);

			decrypted = cipher.doFinal(decodedMsg);
		} catch (Exception e) {
			throw new PasswordEncryptDecryptException(e.getMessage());
		}

		return new String(decrypted);

	}
}
