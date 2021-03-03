package com.ps.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserGetCustomException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public UserGetCustomException() {
		super();
	}
	
	public UserGetCustomException(String message) {
		super(message);
	}	

}
