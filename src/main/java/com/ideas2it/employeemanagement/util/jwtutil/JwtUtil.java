package com.ideas2it.employeemanagement.util.jwtutil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * <p>
 * Generate the token for the user of Employee Management Application
 * </p
 *
 * @author Naganandhini
 * @version 1.0 01-DEC-2022
 */
public class JwtUtil {
	
	private static String key = "Nandhini";

	/**
     * <p>
     * To generate the token for the given username.
     * </p>
     *
     * @param username - the username for which the token is generated 
     * @return - the token
     */
	public static String generateToken(String username) {
		 return Jwts.builder().setSubject(username).setIssuer("nandhini")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)))
				.signWith(SignatureAlgorithm.HS256, key.getBytes())
				.compact();		
	}

	/**
     * <p>
     * To get user details for the given token
     * </p>
     *
     * @param token - the token for which the claims to be returned
     * @return - the user details
     */
	public static Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(key.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
}
