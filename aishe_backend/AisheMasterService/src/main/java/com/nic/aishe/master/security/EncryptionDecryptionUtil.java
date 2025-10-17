package com.nic.aishe.master.security;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;


public class EncryptionDecryptionUtil {
	    private final int keySize;
	    private final int iterationCount;
	    private final Cipher cipher;
	    
	    public EncryptionDecryptionUtil(int keySize, int iterationCount) {
	        this.keySize = keySize;
	        this.iterationCount = iterationCount;
	        try {
	            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        }
	        catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
	        	e.printStackTrace();
	            throw fail(e);
	        }
	    }
	    private byte[] getSalt(){
	    	Random rand = new SecureRandom();
	    	byte[] saltbytes = new byte[16];
    		rand.nextBytes(saltbytes);
    		return saltbytes;
		}
	    private byte[] getIV(){
	    	Random rand = new SecureRandom();
	    	byte[] ivbytes = new byte[16];
    		rand.nextBytes(ivbytes);
    		return ivbytes;
	    }
	    
	    public String decrypt(String salt, String iv, String passphrase, String ciphertext) {
	        try {
	            SecretKey key = generateKey(salt, passphrase);
	            byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
	            return new String(decrypted, StandardCharsets.UTF_8);
	        } catch (Exception e){
	        	e.printStackTrace();
	            return null;
	        }
	    }
	    public String encrypt( String passphrase, String plaintext) {
	    	try {
	    		byte[] saltbytes = getSalt();
	    		 byte[] ivbytes = getIV();
	    		
	    		
	    		SecretKey key = generateKeyForEncryption(saltbytes, passphrase);
	    		byte[] encrypted = doFinalForEncryption(Cipher.ENCRYPT_MODE, key, ivbytes,plaintext.getBytes() );
	    		
	    		String aesPassword = (byteToHex(ivbytes) + "::" + byteToHex(saltbytes) + "::" + new Base64().encodeAsString(encrypted));
	    		return Base64.encodeBase64String(aesPassword.getBytes());
	    	}
	    	catch (Exception e) {
	    		e.printStackTrace();
	    		return null;
	    	}
	    }
	    
	    private SecretKey generateKeyForEncryption(byte[] salt, String passphrase) {
	    	try {
	    		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    		KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), salt, iterationCount, keySize);
	    		SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
	    		return key;
	    	}
	    	catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	    		e.printStackTrace();
	    		return null;
	    	}
	    }
	   
	    private byte[] doFinalForEncryption(int encryptMode, SecretKey key, byte[] iv, byte[] bytes) {
	    	try {
	    		
	    		IvParameterSpec ivSpec = new IvParameterSpec(iv);
	    		cipher.init(encryptMode, key, ivSpec);
	    		return cipher.doFinal(bytes);
	    	}
	    	catch (InvalidKeyException
	    			| InvalidAlgorithmParameterException
	    			| IllegalBlockSizeException
	    			| BadPaddingException e) {
	    		e.printStackTrace();
	    		return null;
	    	}
	    }
	    private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
	        try {
	            cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
	            return cipher.doFinal(bytes);
	        }
	        catch (InvalidKeyException
	                | InvalidAlgorithmParameterException
	                | IllegalBlockSizeException
	                | BadPaddingException e) {
	        	e.printStackTrace();
	            return null;
	        }
	    }
	    private SecretKey generateKey(String salt, String passphrase) {
	        try {
	            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
	            SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
	            return key;
	        }
	        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	        	e.printStackTrace();
	            return null;
	        }
	    }
	   

	    public static byte[] base64(String str) {
	        return Base64.decodeBase64(str);
	    }
	    
	    public static byte[] hex(String str) {
	        try {
	            return Hex.decodeHex(str.toCharArray());
	        }
	        catch (DecoderException e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    public static String byteToHex( byte [] raw ) {
	    	return Hex.encodeHexString(raw);	        
	    }
	    
	    private IllegalStateException fail(Exception e) {
	        return null;
	    }

	public static String getDecryptedString(String encString ) {
		EncryptionDecryptionUtil aesUtil = new EncryptionDecryptionUtil(128,1000);
    	String decryptedPassword =  new String(java.util.Base64.getDecoder().decode(encString));
    	String password = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], "0123456789123456", decryptedPassword.split("::")[2]);
		return password;
	}
	public static String getEncryptedString(String encString ) {
		EncryptionDecryptionUtil aesUtil = new EncryptionDecryptionUtil(128,1000);
		 String encrypt = aesUtil.encrypt("0123456789123456", encString);
		 return encrypt;
	}
//    public static void main(String[] args) throws Exception {
//    	EncryptionDecryptionUtil aesUtil = new EncryptionDecryptionUtil(128,1000);
//    	String decrypt = getDecryptedString("YmNiMDNlNjlhMTRjZWUwMDZjYzcyNGJjM2Q1YzNiNWI6OjUzYzM3YTZjZTAzY2FmY2E2NjU4NTU0ZWRmYjZjMzk1OjozYkpELzhlL0FrOCt4dkpoUUpjK2hBPT0=");
//
//    	String encrypt = getEncryptedString("Admin@123");
//    }
}
