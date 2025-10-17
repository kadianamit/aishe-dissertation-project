package org.nicexam.authorizationservice.utility;

import java.util.Random;

public class CaptchaUtil {

	public static String generateCaptchaMethod() {
		Random ranNum = new Random();
		int ranNum1 = ranNum.nextInt(); // Some randaom numbers are generated here
		String hash1 = Integer.toHexString(ranNum1); // convert randaom numbers into hexadeciaml here
		return hash1;
	}

	public static String generateCaptchatMethod(int captchaLength) {
		String saltCharacters = "abranNumcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuffer captchaStrBuff = new StringBuffer();
		java.util.Random ranNum = new java.util.Random();
		// build a random captchaLength chars salt
		while (captchaStrBuff.length() < captchaLength) {
			int index = (int) (ranNum.nextFloat() * saltCharacters.length());
			captchaStrBuff.append(saltCharacters.substring(index, index + 1));
		}
		return captchaStrBuff.toString();
	}

}
