package com.chch.skj.auth_server.auth;


import org.springframework.stereotype.Service;

import com.chch.skj.auth_server.auth.dto.JWTPair;
import com.chch.skj.auth_server.auth.dto.Role;
import com.chch.skj.auth_server.auth.dto.UserDO;
import com.chch.skj.auth_server.auth.dto.UserDto;
import com.chch.skj.auth_server.auth.exception.UserVerificationFailException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImple implements AuthService{

	private final AuthJWTUtil authJWTUtil ;
	private final AuthDao autoDao ;
	
	@Override
	public JWTPair login(String userId, String password) throws UserVerificationFailException {
		
		var a = new UserDto();
		a.
		autoDao.selectUser(userId,password);
		
		
		
		var jwtPair = authJWTUtil.CreateToken(userId, userId,Role.Basic);
				
		return jwtPair;
	}

	@Override
	public UserDO isValid(String authToken) {
		
		//authJWTUtil.
		
		return null;
	}

}
