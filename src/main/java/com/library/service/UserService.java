package com.library.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.library.entity.User;

public interface UserService {

//	User getUserByUserId(String userId);
	
	public User userSave(User user);
	
	boolean checkEmail(String email);
	
	public User get(String email);
	
	public List<User> getAllUser();
	
	public User getUserById(int user_id);
	
	public void deleteUserById(int user_id);


}
