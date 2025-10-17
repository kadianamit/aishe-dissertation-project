package org.nicexam.authorizationservice.utility;

import java.nio.ByteBuffer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Decryption {
	
	@Autowired Environment environment;
	
	public  String decrypt(String encryptedText) throws Exception {
		
		String secretkey = "HALLO";
//		environment.getProperty("usercontents.secret");
		String salt = "HALLO";
//		environment.getProperty("usercontents.salt");
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		// strip off the salt and iv
		ByteBuffer buffer = ByteBuffer.wrap(new Base64().decode(encryptedText));
		byte[] saltBytes = salt.getBytes();
		
		buffer.get(saltBytes, 0, saltBytes.length);
		byte[] ivBytes1 = new byte[cipher.getBlockSize()];
		buffer.get(ivBytes1, 0, ivBytes1.length);
		byte[] encryptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes1.length];

		buffer.get(encryptedTextBytes);
		// Deriving the key
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(secretkey.toCharArray(), saltBytes, 65556, 256);
		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes1));
		byte[] decryptedTextBytes = null;
		try {
			decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return new String(decryptedTextBytes);
	}
}
