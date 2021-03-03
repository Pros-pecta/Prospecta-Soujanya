package com.ps.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.exceptions.UserDeleteCustomException;
import com.ps.exceptions.UserGetCustomException;
import com.ps.exceptions.UserPostCustomException;
import com.ps.exceptions.UserUpdateCustomException;
import com.ps.model.User;
import com.ps.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<String> saveUserDetails(@RequestBody(required = true) User user){	
		try {
			if(StringUtils.hasText(user.getEmail())) {
				User userObj = userService.findByEmail(user.getEmail());
				if (userObj==null) {
					Integer userId = userService.saveUser(user);
					return new ResponseEntity<String>("Saved with id "+userId, HttpStatus.OK);	
				}
				throw new UserPostCustomException("User already exists with email "+user.getEmail());
			}
			throw new UserPostCustomException("Input is not valid");
		} catch(HttpMessageNotReadableException e) {
			throw new UserPostCustomException("Please check the request payload and re-try");	
		}
					
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(required = true) Integer id){	
		try {
			if(userService.isExist(id)) {
				userService.deleteUser(id);
				return new ResponseEntity<String>("Deleted the user with id "+id, HttpStatus.OK);
			}
			throw new UserDeleteCustomException("User does not exist");			
					
		} catch(Exception e) {
			throw new UserDeleteCustomException("Unable to delete");
		}
	}
	
	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody(required = true) User user){	
		try {
			if(userService.isExist(user.getId())) {
				userService.updateUser(user);
				return new ResponseEntity<String>("Updated the user with id "+user.getId(), HttpStatus.OK);
			}
			throw new UserUpdateCustomException("User does not exist");
					
		} catch(Exception e) {
			throw new UserUpdateCustomException("Unable to update");
		}
	}
			
	
	@GetMapping("/{name}")
	public ResponseEntity<String> getUser(@PathVariable(required = true) String name) {
		if(StringUtils.hasText(name)) {
			userService.findAll().forEach(System.out::println);
				return new ResponseEntity<String>("Welcome "+name, HttpStatus.OK);
		}	
			throw new UserGetCustomException("User does not exist");				
	}
	
}
