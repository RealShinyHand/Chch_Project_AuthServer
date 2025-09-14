package com.chch.skj.auth_server.auth;

import com.chch.skj.auth_server.auth.dto.JWTPair;
import com.chch.skj.auth_server.auth.exception.UserNotFoundException;

public interface AuthService {

	JWTPair Login(String userId,String password)throws UserNotFoundException; 
}
