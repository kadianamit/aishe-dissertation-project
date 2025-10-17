package org.nicexam.authorizationservice.utility;

import java.security.AlgorithmParameters;

import javax.crypto.Cipher;
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
public class Encryption {

	@Autowired
	Environment environment;

	public String encrypt(String word) {
		try {
			byte[] ivBytes;

			String secretkey = "HALLO";
//		environment.getProperty("usercontents.secret");
			String salt = "HALLO";
//		environment.getProperty("usercontents.salt");

//		SecureRandom random = new SecureRandom();
//		byte bytes[] = new byte[20];
//		random.nextBytes(bytes);
			byte[] saltBytes = salt.getBytes();
			// Derive the key
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			PBEKeySpec spec = new PBEKeySpec(secretkey.toCharArray(), salt.getBytes(), 65556, 256);
			SecretKey secretKey = factory.generateSecret(spec);
			SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
			// encrypting the word
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secret);
			AlgorithmParameters params = cipher.getParameters();
			ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
			byte[] encryptedTextBytes = cipher.doFinal(word.getBytes("UTF-8"));
			// prepend salt and vi
			byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];
			System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
			System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
			System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length,
					encryptedTextBytes.length);
			return new Base64().encodeToString(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return word;
	}
//		public static void main(String[] args) throws Exception{
//		    Encryption en=new Encryption();
//		    String encryptedWord=en.encrypt("Test gh fghfgh fgh fghfgh fgh fgh fgh"); 
//		    System.out.println("Encrypted word is : " + encryptedWord);
//		    Decryption de =new Decryption();
//		    System.out.println("Decrypted word is : " +    de.decrypt(encryptedWord));
//		  }

}
