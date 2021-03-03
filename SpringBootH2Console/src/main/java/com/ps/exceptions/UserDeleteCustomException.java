package com.ps.exceptions;

public class UserDeleteCustomException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public UserDeleteCustomException() {
		super();
	}
		
	public UserDeleteCustomException(String message) {
		super(message);
	}

}
