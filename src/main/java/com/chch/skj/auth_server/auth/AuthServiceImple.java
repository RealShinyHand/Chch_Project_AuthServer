package com.chch.skj.auth_server.auth;


import org.springframework.stereotype.Service;

import com.chch.skj.auth_server.auth.res.JWTPair;
import com.chch.skj.auth_server.auth.dto.Role;
import com.chch.skj.auth_server.auth.res.UserRes;
import com.chch.skj.auth_server.auth.dto.UserDto;
import com.chch.skj.auth_server.auth.exception.UserVerificationFailException;
import com.chch.skj.auth_server.mapper.MemberDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImple implements AuthService{

	private final AuthJWTUtil authJWTUtil ;
	private final MemberDao memberDao ;
	
	@Override
	public JWTPair login(String userId, String password) throws UserVerificationFailException {
		
		var a = new UserDto();
		
		UserDto dto = memberDao.selectUserUserIdAndPassword(userId,password);
		
		
		
		var jwtPair = authJWTUtil.CreateToken(userId, userId,Role.Basic);
				
		return jwtPair;
	}

	@Override
	public UserRes isValid(String authToken) throws UserVerificationFailException{
		
		try {
			var userDO = authJWTUtil.isValid(authToken);
			return userDO;
		}catch (Exception e) {
			
			throw new UserVerificationFailException("User JTW Verification Fail");
		}
	}

	@Override
	public JWTPair refreshToken(String refreshToken) {
		return null;
	}

}
