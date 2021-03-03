package com.ps.service;

import java.util.List;

import com.ps.model.User;

public interface UserService {
	 Integer saveUser(User user);
	 User findByEmail(String email);
	 void deleteUser(Integer id);
	 void updateUser(User u);
	 boolean isExist(Integer id);
	 List<User> findAll();

}
