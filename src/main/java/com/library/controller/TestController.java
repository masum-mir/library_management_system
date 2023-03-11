package com.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test-controller")
public class TestController {

	@GetMapping
	public ResponseEntity<String> seyHello() {
		return ResponseEntity.ok("hello form secure test");
	}
	
	
}
