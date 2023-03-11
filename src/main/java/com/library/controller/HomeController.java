package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.library.dto.BookDto;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.service.BookService;
import com.library.serviceImpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	UserServiceImpl service;

	@Autowired
	BookService bookService;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/")
	public String Home(Model model) {

		List<BookDto> book = bookService.getAllBooks();
		model.addAttribute("book", book);

		return "Index";
	}

	@GetMapping("/signin")
	public String doLoginPage() {

		return "login";
	}

	@GetMapping("/addUser")
	public String AddUser() {
		return "signup";
	}

	@PostMapping("/register")
	public String UserRegister(@ModelAttribute("user") User user, HttpSession session) {
		System.out.println(user);
		boolean f = service.checkEmail(user.getEmail());
		if (f) {
			session.setAttribute("msg", user.getEmail());
			System.out.println("User is already Exists!");
		} else {

			user.setPassword(encoder.encode(user.getPassword()));
			user.setRole("ROLE_USER");
			User usr = service.userSave(user);

			if (usr != null) {
				System.out.println("User save successfully!!");
			} else {
				System.out.println("User not save??");
			}
		}

		return "redirect:/signin";
	}

}
