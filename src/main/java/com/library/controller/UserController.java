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

import com.library.dto.BookDto;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.repositories.UserRepo;
import com.library.service.BookService;
import com.library.service.UserService;
import com.library.serviceImpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;

	@ModelAttribute
	public void addCommonData(Model m, Principal principal) {
		String username = principal.getName();
		User user = userRepo.getUserByUserName(username);
		m.addAttribute("user", user);
	}

	@GetMapping("/user/dashboard")
	public String userDashboard(Model model) {
		
		List<Book> books = bookService.getAllBooks();
		
		model.addAttribute("books",books);
		
		return "normal/user_dashboard";
	}
	
	@GetMapping("/user/book_details")
	public String bookDetails() {
		
		return "normal/book_details";
	}
	
	
	// user details api admin

	@GetMapping("/admin/users")
	public String userList(Model model) {
		List<User> user = userService.getAllUser();
		model.addAttribute("users", user);
		return "admin/user_list";
	}

	@GetMapping("/admin/user_delete/{user_id}")
	public String deleteUser(@PathVariable("user_id") int user_id) {

		userService.deleteUserById(user_id);

		return "redirect:/admin/users";
	}

}
