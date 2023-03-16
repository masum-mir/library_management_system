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
	
	@GetMapping("/admin/customer_information")
	public String studentInfo() {
		
		return "admin/customer_registration";
	}
	
	@PostMapping("/admin/student_info_save")
	public String studentInfoSave(@ModelAttribute Customers customers) {
		
		this.customerService.createCustomer(customers);
		
		return "redirect:/admin/customer_information";
	}
	

//	@GetMapping("/admin/students")
//	@ModelAttribute
//	public void getCustomer(Model m) {
//		
//		List<Customers> customers = customerService.getCustomer();
//		System.out.println("customers: : : : ::::::::::::::::::::::::::::::"+ customers);
//		
//		m.addAttribute("customers",customers);
//		
//	}
	
	
}
