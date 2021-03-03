package com.ps.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserPostCustomException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public UserPostCustomException() {
		super();
	}
		
	public UserPostCustomException(String message) {
		super(message);
	}

}
