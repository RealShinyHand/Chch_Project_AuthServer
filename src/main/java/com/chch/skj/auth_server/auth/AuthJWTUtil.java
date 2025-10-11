package com.chch.skj.auth_server.auth;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.chch.skj.auth_server.auth.res.JWTPair;
import com.chch.skj.auth_server.auth.dto.Role;
import com.chch.skj.auth_server.auth.res.UserRes;

@Component
public class AuthJWTUtil {

	@Value(value = "${auth.jwt.key1}")
	private String key1Str ;

	@Value(value = "${auth.jwt.key2}")
	private String key2Str ;
	
	
	
	public JWTPair CreateToken(String userID,String userUID,Role role) {
		
		LocalDateTime now = LocalDateTime.now();
		
		
		Map<String,Object> headerMap = new HashMap<>();
		Map<String,Object> payLoadMap = new HashMap<>();

		
		payLoadMap.put(Claims.AUDIENCE, role);

		payLoadMap.put(Claims.EXPIRATION, Long.toString(now.plusHours(1).toEpochSecond(ZoneOffset.ofHours(9))));

		payLoadMap.put(Claims.ID, userUID);

		payLoadMap.put(Claims.ISSUED_AT, "CCH_Auth_Server");

		payLoadMap.put(Claims.ISSUER, "CCH");

		payLoadMap.put(Claims.SUBJECT, "auth");

		payLoadMap.put(Claims.NOT_BEFORE, Long.toString(now.toEpochSecond(ZoneOffset.ofHours(9))) );
		
		payLoadMap.put("role", role);
		
		payLoadMap.put("userID", userID);
		
		
		SecretKey key  = Keys.hmacShaKeyFor(key1Str.getBytes());

		String autToken = Jwts.builder()
		.setHeader(headerMap)
		.setClaims(payLoadMap)
		.signWith(key, SignatureAlgorithm.HS256)
		.compact();
		
		
		payLoadMap.put(Claims.SUBJECT, "refresh");
		
		SecretKey key2  = Keys.hmacShaKeyFor(key2Str.getBytes());
		
		
		String refreshToken = Jwts.builder()
		.setHeader(headerMap)
		.setClaims(payLoadMap)
		.signWith(key2, SignatureAlgorithm.HS256)
		.compact();
		
		
		JWTPair jwtPair = new JWTPair(autToken, refreshToken);
		
		
		return jwtPair;
	}
	
	public UserRes isValid(String authToken) throws Exception{
		

		SecretKey key1  = Keys.hmacShaKeyFor(key1Str.getBytes());
		
		try {
			var jwt =  Jwts.parserBuilder().setSigningKey(key1).build().parse(authToken);

			Claims claims = (Claims) jwt.getBody();
			String userID = claims.get("userID").toString();
			String userUID = claims.get(Claims.ID).toString();
			String roleString = claims.get("role").toString();
			
			var role = Role.valueOf(roleString);
			
			var userDO = new UserRes(userID,userUID,role);
			
			return userDO;	
			
		}catch (Exception e) {
			throw e;
		}

	}
}
