package com.chch.skj.auth_server.auth;

import com.chch.skj.auth_server.auth.res.ResponseEntityInnerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chch.skj.auth_server.auth.exception.UserVerificationFailException;

@RestControllerAdvice
public class AuthControllerAdvice {


	@ExceptionHandler(exception = UserVerificationFailException.class)
	public ResponseEntity<?> handleUserVerificationFail(UserVerificationFailException e){



		var res = ResponseEntityInnerDto.fromToResEntity(e.getMessage(),HttpStatus.UNAUTHORIZED,"User 인증 실패");
		return res;
	}
	
}
