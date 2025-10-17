package aishe.gov.in.utility;

import java.util.Random;

public class CaptchaUtil {
    public static String generateCaptchaMethod1() {
        Random ranNum = new Random();
        int ranNum1 = ranNum.nextInt(); // Some randaom numbers are generated here
        String hash1 = Integer.toHexString(ranNum1); // convert randaom numbers into hexadeciaml here
        return hash1;
    }

    public static String generateCaptchatMethod2(int captchaLength) {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String saltCharacters = letters + numbers;

        StringBuilder captchaStrBuff = new StringBuilder();
        Random randomGenerator = new Random();
        boolean hasLetter = false;
        boolean hasNumber = false;
        while (captchaStrBuff.length() < captchaLength) {
            int index = randomGenerator.nextInt(saltCharacters.length());
            char selectedChar = saltCharacters.charAt(index);
            captchaStrBuff.append(selectedChar);
            if (Character.isLetter(selectedChar)) {
                hasLetter = true;
            } else if (Character.isDigit(selectedChar)) {
                hasNumber = true;
            }
            if (hasLetter && hasNumber && captchaStrBuff.length() == captchaLength) {
                break;
            }
        }
        if (!hasLetter || !hasNumber) {
            return generateCaptchatMethod2(captchaLength);
        }
        return captchaStrBuff.toString();
    }
}
