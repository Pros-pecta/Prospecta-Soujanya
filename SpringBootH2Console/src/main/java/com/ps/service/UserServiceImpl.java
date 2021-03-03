package com.ps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.model.User;
import com.ps.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

	@Override
	public Integer saveUser(User user) {
	    return userRepo.save(user).getId();
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	/*	@Override
		public User finUser(Integer id) {
			User user = null;
			Optional<User> opt=userRepo.findById(id);
			if(opt.isPresent()) {
				user = opt.get();
			}
		    return user;
		}
	*/
	@Override
	public void deleteUser(Integer id) {
		userRepo.deleteById(id);
		
	}
 
	@Override
	public void updateUser(User user) {
		userRepo.save(user);
		
	}
	
	@Override
	public boolean isExist(Integer id) {
		return userRepo.existsById(id);
	}
	
	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

}
