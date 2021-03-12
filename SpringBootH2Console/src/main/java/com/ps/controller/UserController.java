package com.ps.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class); 
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<String> saveUserDetails(@Valid @RequestBody(required = true) User user){	
		LOGGER.info("User details are saved:"+user.toString());
		try {
			//if(StringUtils.hasText(user.getEmail())) {
				User userObj = userService.getUserByEmail(user.getEmail());
				if (userObj==null) {
					Integer userId = userService.saveUser(user);
					return new ResponseEntity<String>("Saved with id "+userId, HttpStatus.OK);	
				}
				throw new UserPostCustomException("User already exists with email "+user.getEmail());
			//}
			//throw new UserPostCustomException("Input is not valid");
		} catch(HttpMessageNotReadableException e) {
			throw new UserPostCustomException("Please check the request payload and re-try");	
		}
					
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(required = true) Integer id){	
		LOGGER.info("Deleting user with userId:"+id);
		try {
			if(userService.isUserExist(id)) {
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
		LOGGER.info("Updating user:"+user.toString());
		try {
			if(userService.isUserExist(user.getId())) {
				userService.updateUser(user);
				return new ResponseEntity<String>("Updated the user with id "+user.getId(), HttpStatus.OK);
			}
			throw new UserUpdateCustomException("User does not exist");
					
		} catch(Exception e) {
			e.printStackTrace();
			throw new UserUpdateCustomException("Unable to update");
		}
	}
			
	
	@GetMapping
	public ResponseEntity<?> getUsers() {
		LOGGER.info("Get users:");
		try {
			List<User> userList = userService.getUsers();
			return new ResponseEntity<List<User>>(userList,HttpStatus.OK);

		}catch (Exception e) {
			throw new UserUpdateCustomException("Could not get the users list");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable(required = true) Integer id) {
		LOGGER.info("Get user with id:"+id);
		if(userService.isUserExist(id)) {
			User user = userService.getUser(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		throw new UserGetCustomException("User does not exist");
	}
	
}
