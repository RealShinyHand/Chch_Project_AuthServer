package com.chch.skj.auth_server.auth;

import org.springframework.stereotype.Service;

import com.chch.skj.auth_server.auth.dto.JWTPair;
import com.chch.skj.auth_server.auth.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImple implements AuthService{

	private final AuthJWTUtil authJWTUtil = null;
	private final AuthRepository autoRepo = null;
	
	@Override
	public JWTPair Login(String userId, String password) throws UserNotFoundException {
		
		String authToken = "";
		String refreshToken = "";
		boolean result = false;
		
		
		
		
		var resultVo = new JWTPair(authToken, refreshToken,result);
		
		
		return resultVo;
	}

}
