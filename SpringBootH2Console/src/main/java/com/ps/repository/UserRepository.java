package com.ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByEmail(String email);


}
