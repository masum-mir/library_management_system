package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.entity.Customers;
import com.library.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customers>> getCustomer() {
		
		List<Customers> customer = customerService.getCustomer();
		
		return new ResponseEntity<List<Customers>>(customer,HttpStatus.OK);
	}
	
	@PostMapping("/customer/save")
	public ResponseEntity<Customers> saveCustomer(@RequestBody Customers customers) {
		
		Customers save = customerService.createCustomer(customers);
		
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}
	
}
