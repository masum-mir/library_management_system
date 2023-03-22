package com.library.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BookDto;
import com.library.dto.CategoryDto;
import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.Category;
import com.library.entity.Customers;
import com.library.entity.User;
import com.library.repositories.UserRepo;
import com.library.service.AuthorService;
import com.library.service.BookService;
import com.library.service.CategoryService;
import com.library.service.CustomerService;
import com.library.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Value("${project.image}")
	private String path;
	
	@Autowired
	UserRepo userRepo;

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;

	@ModelAttribute
	public void addCommonData(Model m, Principal principal) {
		String username = principal.getName();
		User user = userRepo.getUserByUserName(username);
		m.addAttribute("user", user);
	}

	// dashboard api
	
	@GetMapping("/dashboard")
	public String Home(Model m) {
		
		List<Book> book = bookService.getAllBooks();		
		m.addAttribute("book",book);
		
		return "admin/admin_dashboard";
	}

	@GetMapping("/newFile")
	public String newAbout() {

		return "NewFile";
	}	

	@ModelAttribute
	public void getCustomer(Model m) {
	
		List<Customers> customers = customerService.getCustomer();
		System.out.println("customers: : : : ::::::::::::::::::::::::::::::"+ customers);
		
		m.addAttribute("customers",customers);
		
//		Book book = bookService.getBookById(id);
//		m.addAttribute("bookById",book);
	}
	
//	@PostMapping("/searchBook")
//	public String SearchBooks(@RequestParam("keyword") String keyword, Model model) {
//		final List<Book> books = bookService.searchBooks(keyword);
//		
//		model.addAttribute("books",books);
//		model.addAttribute("keyword", keyword);
//		return "admin/dashboard";
//	}

	

}
