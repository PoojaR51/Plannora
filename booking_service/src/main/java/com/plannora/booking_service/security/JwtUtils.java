package com.plannora.booking_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	private SecretKey secretKey;

	@PostConstruct
	public void init() {
		this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	/**
	 * Validate JWT token and return claims
	 */
	public Claims validateToken(String token) {
		return Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	/**
	 * Get logged-in user from SecurityContext
	 */
	public UserPrincipal getCurrentPrincipal() {

		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null ||
				!authentication.isAuthenticated() ||
				authentication.getPrincipal().equals("anonymousUser")) {
			return null;
		}

		return (UserPrincipal) authentication.getPrincipal();
	}
}
