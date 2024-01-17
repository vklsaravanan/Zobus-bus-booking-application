package com.zobus.auth;

import java.security.SecureRandom;
import java.util.Base64;

public class Authendicate {
	
	/*
	 * generate new login token for new users or new login
	 * usually called from UserDAO from creating LoginToken
	 */
	public static String generateLoginToken() {
		String randomString = generateRandomString(20);

        byte[] encodedBytes = Base64.getEncoder().encode(randomString.getBytes());

        return new String(encodedBytes);
	}
    
	/*
	 * using for generateLoginToken metho
	 */
	private static String generateRandomString(int length) {
        // Generate a random string using a secure random number generator
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);
        return new String(randomBytes);
    }

}
