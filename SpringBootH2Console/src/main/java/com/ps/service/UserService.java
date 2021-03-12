package com.ps.service;

import java.util.List;

import com.ps.model.User;

public interface UserService {
	 Integer saveUser(User user);
	 User getUserByEmail(String email);
	 void deleteUser(Integer id);
	 void updateUser(User u);
	 boolean isUserExist(Integer id);
	 List<User> getUsers();
	 User getUser(Integer id);
	 

}
