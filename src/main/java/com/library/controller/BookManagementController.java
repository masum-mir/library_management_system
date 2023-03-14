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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.dto.BookDto;
import com.library.entity.Book;
import com.library.entity.BookManagement;
import com.library.entity.Customers;
import com.library.entity.User;
import com.library.repositories.UserRepo;
import com.library.service.BookManagementService;
import com.library.service.BookService;


@Controller
public class BookManagementController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookManagementService bookManagementService;
	
	@ModelAttribute
	public void addCommonDate(Model m, Principal principle) {		
		String username = principle.getName();		
		User user = userRepo.getUserByUserName(username);		
		m.addAttribute("user",user);		
	}
	
	
	@GetMapping("/admin/book_details/{id}")
	public String getBookInfo(@PathVariable int id, Model model) {
		
		Book book = bookService.getBookById(id);
		
		model.addAttribute("book",book);
		
		return "admin/book_details";
	}
	
	@PostMapping("/admin/book_details_save")
	public String saveBookorder(@ModelAttribute BookManagement book) {
		
		bookManagementService.saveBookManagement(book);
		
		return "redirect:/admin/dashboard";
	}
	
	
}
