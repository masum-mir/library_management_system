package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.config.AuthenticationRequest;
import com.library.config.AuthenticationResponse;
import com.library.config.AuthenticationService;
import com.library.entity.User;

@Controller
@RequestMapping("/api/v1/auth")
public class TestController {
	
//	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping
	public ResponseEntity<String> seyHello() {
		return ResponseEntity.ok("hello form secure test");
	}
	
	/**
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) {
		
		return ResponseEntity.ok(authenticationService.register(user));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
	**/
}
