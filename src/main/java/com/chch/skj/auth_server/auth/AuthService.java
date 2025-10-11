package com.chch.skj.auth_server.auth;

import com.chch.skj.auth_server.auth.res.JWTPair;
import com.chch.skj.auth_server.auth.res.UserRes;
import com.chch.skj.auth_server.auth.exception.UserVerificationFailException;

public interface AuthService {

	JWTPair login(String userId,String password)throws UserVerificationFailException; 
	
	UserRes isValid(String authToken) throws UserVerificationFailException;

	JWTPair refreshToken(String refreshToken);
}
