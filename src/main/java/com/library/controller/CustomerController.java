package com.library.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.entity.Customers;
import com.library.entity.User;
import com.library.repositories.UserRepo;
import com.library.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	UserRepo repo;
	
	@Autowired
	private CustomerService customerService;
	
	@ModelAttribute
	public void addCommonData(Model m, Principal principal) {
		String username = principal.getName();
		User user = repo.getUserByUserName(username);
		m.addAttribute("user",user);
	}
	
	
	@GetMapping("/admin/customer_info")
	public String customerInfo() {
		
		return "admin/customer_information";
	}
	
	
}
