package com.chch.skj.auth_server.auth;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.chch.skj.auth_server.auth.dto.JWTPair;
import com.chch.skj.auth_server.auth.dto.UserDO;
@Component
public class AuthJWTUtil {

	public JWTPair CreateToken() {
		
		
		//SecretKey key = Jwts.SIG.HS256.key().build();

		//String jws = Jwts.builder().subject("Joe").signWith(key).compact();
		return null;
	}
	
	public UserDO isValid(String authToken) {
		
		return null;
	}
}
