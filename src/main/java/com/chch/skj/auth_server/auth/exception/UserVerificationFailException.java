package com.chch.skj.auth_server.auth.exception;

public class UserVerificationFailException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 202509142241L;

	public UserVerificationFailException (String message) {
		super(message);
	}
}
