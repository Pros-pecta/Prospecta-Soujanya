package com.ps.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler{
	
	@ExceptionHandler(UserPostCustomException.class)
	public ResponseEntity<ApiError> handleUserCustomException(UserPostCustomException ex){
		return new ResponseEntity<ApiError>(new ApiError(ex.getMessage()), HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(UserGetCustomException.class)
	public ResponseEntity<ApiError> handleDoesNotExistsException(UserGetCustomException ex){
		return new ResponseEntity<ApiError>(new ApiError(ex.getMessage()), HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(UserDeleteCustomException.class)
	public ResponseEntity<ApiError> handleUnableToDelete(UserDeleteCustomException ex){
		return new ResponseEntity<ApiError>(new ApiError(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler(UserUpdateCustomException.class)
	public ResponseEntity<ApiError> handleUnableToUpdate(UserUpdateCustomException ex){
		return new ResponseEntity<ApiError>(new ApiError(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiError> handleMethodNotSupportedException(Exception ex){
		return new ResponseEntity<ApiError>(new ApiError("The user name is empty."), HttpStatus.NOT_FOUND);	
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiError> handleEmptyRequestException(Exception ex){
		return new ResponseEntity<ApiError>(new ApiError("The request body is empty"), HttpStatus.NOT_FOUND);	
	}
}
