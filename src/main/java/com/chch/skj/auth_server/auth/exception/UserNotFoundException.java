package com.chch.skj.auth_server.auth.exception;

public class UserNotFoundException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 202509142241L;

	public UserNotFoundException (String message) {
		super(message);
	}
}
