package com.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.entity.ERole;
import com.library.entity.User;
import com.library.repositories.UserRepo;

@Service
public class AuthenticationService {

	/**
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public AuthenticationResponse register(User u) {
		
		User user = new User();
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setMobile(u.getMobile());
		user.setPassword(passwordEncoder.encode(u.getPassword()));
		user.setRole(u.getRole());
		
		userRepo.save(user);
		
		var jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponse.;
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		
		return null;
	}
	
	**/
}
