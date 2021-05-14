package com.platzimaven.market.web.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	private static final String KEY = "bigseedfourty122222222222222222222222222222222222222222222222222222222222222222222222222222222";

	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, KEY).compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		return userDetails.getUsername().equals(this.extractUsernameFromToken(token)) && !this.isTokenExpired(token);
	}

	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}

	public String extractUsernameFromToken(String token) {
		return this.getClaims(token).getSubject();
	}

	public boolean isTokenExpired(String token) {
		return this.getClaims(token).getExpiration().before(new Date());
	}

}
