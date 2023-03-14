package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.library.entity.BookManagement;
import com.library.entity.RegistrationDetails;
import com.library.service.BookManagementService;
import com.library.service.RegistrationDetailsService;

@Controller
public class RegistrationDetailsController {
	
	@Autowired
	RegistrationDetailsService registrationDetailsService;
	
	@Autowired
	BookManagementService bookManagementService;
	
	@GetMapping("/registration")
	public ResponseEntity<List<RegistrationDetails>> getRegistration() {
		
		List<RegistrationDetails> details= registrationDetailsService.getRegistrationDetails();
		
		return new ResponseEntity<>(details, HttpStatus.OK);
	}
	
	@PostMapping("/registration/save")
	public ResponseEntity<RegistrationDetails> createRegistration(@RequestBody RegistrationDetails details) {
		
		RegistrationDetails saveDetails = registrationDetailsService.saveRegistration(details);
		
		return new ResponseEntity<>(saveDetails, HttpStatus.CREATED);
	}
	
	
	// postman api ---- book management controller
	@RequestMapping(value = "/book_manage_save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BookManagement> saveBook(@RequestBody BookManagement book) {
		
		BookManagement save = bookManagementService.saveBookManagement(book);
		
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}
	
	@GetMapping("/book_manage_details")
	public ResponseEntity<List<BookManagement>> bookInfo() {
		List<BookManagement> book = bookManagementService.getBookInfo();
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	

}
