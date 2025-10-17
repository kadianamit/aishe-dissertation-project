package org.nicexam.authorizationservice.utility;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.nicexam.authorizationservice.security.JwtConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author NICSI
 *
 */
public abstract class CommonUtils {

	public static String getRandomNumberString() {

		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	public static long generateElevenDigitRandomNumber() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextLong(10_000_000_000L, 100_000_000_000L);
	}

//chnage needed
	public static String getToken(Long userId, JwtConfig jwtConfig) {

		Long now = System.currentTimeMillis();
		String token = Jwts.builder().setSubject(userId.toString())
				// Convert to list of strings.
				// This is important because it affects the way we get them back in the Gateway.
				.claim("authorities", new ArrayList<>()).setIssuedAt(new Date(now))
				.setExpiration(new Date(now + jwtConfig.getExpiration() * 15000)) // in milliseconds
//			.setExpiration(new Date(now + 120 * 1000))  // in milliseconds
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes()).compact();
		return token;
	}

	public static String getPenDetails() {
		Random rnd = new Random();
		int number = rnd.nextInt(99999999);
		return String.format("%06d", number);
	}
}