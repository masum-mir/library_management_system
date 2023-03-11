package com.library.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.User;
import com.library.repositories.UserRepo;
import com.library.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;

	@Override
	public User userSave(User user) {
		
//		user.setName("Masum");
//		user.setEmail("test");
//		user.setPassword("23342");
		
		return userRepo.save(user);
	}

	@Override
	public boolean checkEmail(String email) {
		return userRepo.existsByEmail(email);
	}

	@Override
	public User get(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepo.findAll();
	}

	@Override
	public User getUserById(int user_id) {
		Optional<User> u = userRepo.findById(user_id);
		if(u.isPresent()) {
			return u.get();
		} 
		return null;
	}

	@Override
	public void deleteUserById(int user_id) {
		this.userRepo.deleteById(user_id);
	}

}
