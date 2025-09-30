package com.chch.skj.auth_server.auth;

import com.chch.skj.auth_server.auth.dto.JWTPair;
import com.chch.skj.auth_server.auth.dto.UserDO;
import com.chch.skj.auth_server.auth.exception.UserVerificationFailException;

public interface AuthService {

	JWTPair login(String userId,String password)throws UserVerificationFailException; 
	
	UserDO isValid(String authToken) throws UserVerificationFailException;
}
