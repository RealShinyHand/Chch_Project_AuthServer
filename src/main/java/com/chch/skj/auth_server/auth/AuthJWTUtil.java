package com.chch.skj.auth_server.auth;

import java.time.LocalDateTime;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.chch.skj.auth_server.auth.dto.JWTPair;
import com.chch.skj.auth_server.auth.dto.Role;
import com.chch.skj.auth_server.auth.dto.UserDO;

@Component
public class AuthJWTUtil {

	@Value(value = "auth.jwt.key1")
	private String key1 = "";

	@Value(value = "auth.jwt.key2")
	private String key2 = "";
	
	
	
	public JWTPair CreateToken(String userID,String userUID,Role role) {
		
		LocalDateTime now = LocalDateTime.now();
		
		
		Map<String,Object> headerMap = new HashMap<>();
		Map<String,Object> payLoadMap = new HashMap<>();
		
		payLoadMap.put(Claims.AUDIENCE, role);

		payLoadMap.put(Claims.EXPIRATION, now.plusHours(1));

		payLoadMap.put(Claims.ID, userUID);

		payLoadMap.put(Claims.ISSUED_AT, "CCH_Auth_Server");

		payLoadMap.put(Claims.ISSUER, "CCH");

		payLoadMap.put(Claims.SUBJECT, "auth");

		payLoadMap.put(Claims.NOT_BEFORE, now );
		
		payLoadMap.put("role", role);
		
		payLoadMap.put("userID", userID);
		
		
		var key  = Keys.hmacShaKeyFor(key1.getBytes());
		
		String autToken = Jwts.builder()
		.setHeader(headerMap)
		.setClaims(payLoadMap)
		.signWith(key, SignatureAlgorithm.HS256)
		.compact();
		
		
		payLoadMap.put(Claims.SUBJECT, "refresh");
		
		var key2  = Keys.hmacShaKeyFor(key1.getBytes());
		
		
		String refreshToken = Jwts.builder()
		.setHeader(headerMap)
		.setClaims(payLoadMap)
		.signWith(key2, SignatureAlgorithm.HS256)
		.compact();
		
		
		JWTPair jwtPair = new JWTPair(autToken, refreshToken);
		
		
		return jwtPair;
	}
	
	public UserDO isValid(String authToken) {
		
		return null;
	}
}
