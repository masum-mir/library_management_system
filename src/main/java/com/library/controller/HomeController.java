package com.library.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BookDto;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.payloads.FileResponse;
import com.library.service.BookService;
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
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile file) {
		
		String fileName = null;
		
		try {
			fileName = this.bookService.uploadImage(path, file);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new FileResponse(fileName, "Image save success"), HttpStatus.OK);
	}
	
	@GetMapping(value = "/showImage/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String image, HttpServletResponse response) throws IOException {
		
		InputStream resource = this.bookService.getResource(path, image);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
		
	}

}
