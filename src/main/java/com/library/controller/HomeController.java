package com.library.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BookDto;
import com.library.entity.Book;
import com.library.entity.Customers;
import com.library.entity.User;
import com.library.payloads.FileResponse;
import com.library.service.BookService;
import com.library.service.CustomerService;
import com.library.serviceImpl.UserServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Value("${project.image}")
	private String path;

	@Autowired
	UserServiceImpl service;

	@Autowired
	BookService bookService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	CustomerService customerService;

	@GetMapping("/")
	public String Home(Model model) {

		List<Book> book = bookService.getAllBooks();
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
			user.setRole("ROLE_ADMIN");
			User usr = service.userSave(user);

			if (usr != null) {
				System.out.println("User save successfully!!");
			} else {
				System.out.println("User not save??");
			}
		}

		return "redirect:/signin";
	}

	// postman api ..!

	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile file) {

		String fileName = null;

		try {
			fileName = this.bookService.uploadImage(path, file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(new FileResponse(fileName, "Image save success"), HttpStatus.OK);
	}

	@GetMapping(value = "/showImage/{image}")
	public void showImage(@PathVariable String image, HttpServletResponse response) throws IOException {

//		InputStream resource = this.bookService.getResource(path, image);

		File file = new File(path + File.separator + image);

		InputStream inputStream = new FileInputStream(file);

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		StreamUtils.copy(inputStream, response.getOutputStream());

	}

	@GetMapping("/dashboard")
	public ResponseEntity<?> showDashboard() {

		List<Book> books = bookService.getAllBooks();

		System.out.println("Books::: " + books);

		return new ResponseEntity<>(books, HttpStatus.OK);

	}

	// customer control postman api

	@GetMapping("/customer")
	public ResponseEntity<List<Customers>> getCustomer() {

		List<Customers> customer = customerService.getCustomer();

		return new ResponseEntity<List<Customers>>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/save", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customers> saveCustomer(@RequestBody Customers customers) {

		Customers save = customerService.createCustomer(customers);

		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	// searching customer
	@GetMapping("/customer/{id}")
	public ResponseEntity<List<Customers>> searchCustomer(@PathVariable Long id) {
		List<Customers> customer = customerService.findByCustomerIdContaining(id);
		
		return new ResponseEntity<List<Customers>>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/book/{keyword}")
	public ResponseEntity<List<Book>> searchBook(@PathVariable String keyword) {
		
		List<Book> book = bookService.searchBooks(keyword);
		
		return new ResponseEntity<List<Book>>(book, HttpStatus.OK);
		
	}
}
