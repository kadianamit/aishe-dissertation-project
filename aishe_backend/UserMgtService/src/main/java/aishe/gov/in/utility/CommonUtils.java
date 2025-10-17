package aishe.gov.in.utility;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author NICSI
 *
 */
public abstract class CommonUtils {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String getRandomNumberString() {

		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	public static long generateElevenDigitRandomNumber() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextLong(10_000_000_000L, 100_000_000_000L);
	}

	public static String forgottoken() {
		int count = 20;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static String generateRequestId(Integer id) {
		return "DS-" + DateUtils.formatDateWithOutBackslash() + "-" + String.format("%05d", id);
	}
}