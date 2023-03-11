package com.library.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.entity.Book;
import com.library.entity.User;
import com.library.repositories.UserRepo;
import com.library.service.BookService;
import com.library.service.UserService;
import com.library.serviceImpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/user")
public class UserController {

	/*
	@Autowired
	UserServiceImpl service;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/dashboard")
	public String Home() {
		
//		List<Book> book  = bookService.getAllBooks();
//		model.addAttribute("book", book);
		
		return "normal/user_dashboard";
	}
	
	@GetMapping("/edit/{user_id}")
	public String edit(@PathVariable int user_id, Model m) {
		User user = service.getUserById(user_id);
		m.addAttribute("ur",user);		
		
		return "Edit";
	}
	
	@PostMapping("/update")
	public String updateUser(@ModelAttribute User user) {
		
		service.userSave(user);
		
		return "redirect:/";
	}
//	
//	@GetMapping("/delete/{user_id}")
//	public String deleteUser(@PathVariable int user_id) {
//		
//		service.deleteUserById(user_id);
//		
//		return "redirect:/";
//	}
	
	*/
}
