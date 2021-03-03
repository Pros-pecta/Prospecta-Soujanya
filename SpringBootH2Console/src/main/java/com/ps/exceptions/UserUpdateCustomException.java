package com.ps.exceptions;

public class UserUpdateCustomException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
		
		public UserUpdateCustomException() {
			super();
		}
			
		public UserUpdateCustomException(String message) {
			super(message);
		}

}
