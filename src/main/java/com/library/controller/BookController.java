package com.library.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BookDto;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.payloads.FileResponse;
import com.library.repositories.UserRepo;
import com.library.service.AuthorService;
import com.library.service.BookService;
import com.library.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class BookController {

//	@Value("${project.image}")
//	private String path;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BookService bookservice;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	AuthorService authorService;
	
//	private String path = "E:\\Java Project\\library_management_system\\src\\main\\resources\\static\\images";
	private String path = new ClassPathResource("static/images/").getFile().getAbsolutePath();
	
	public BookController() throws IOException {
		
	}
	
	@ModelAttribute
	public void addCommonData(Model m, Principal principal) {
		String username = principal.getName();
		System.out.println("username :::::::::::::::" + username);
		User user = userRepo.getUserByUserName(username);
		System.out.println("user :::::::::::::::" + user.getName());
		m.addAttribute("user", user);
	}
	
	@RequestMapping("/searchBook")
	public String SearchBooks(@Param("keyword") String keyword, Model model) {
		final List<Book> books = bookservice.searchBooks(keyword);
		
		model.addAttribute("books",books);
		model.addAttribute("keyword", keyword);
		return "admin/book_list";
	}

	@GetMapping("/book")
	public String getAllBooks(Model model) {

		List<Book> books = this.bookservice.getAllBooks();

		model.addAttribute("book", books);

		return "admin/book_list";
	}

	@PostMapping("/book_save")
	public String saveBook(Book book, BindingResult result, Model model, @RequestParam MultipartFile photo) throws IOException {

		if (result.hasErrors()) {
			return "admin/book_add";
		}
		System.out.println("Orginal Name::::::: "+photo.getOriginalFilename());
		System.out.println("get Size::::: "+ photo.getSize());
		System.out.println("get Content type:::: "+ photo.getContentType());
		System.out.println("get Name:::: "+ photo.getName());
		
		if(photo.isEmpty()) {
			return "admin/book_add";
		}
		
		String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
		
//		book.setPhotos(photo.getOriginalFilename());
		
		System.out.println("file name ::::::::::::: "+ fileName);
		
		String randomId= UUID.randomUUID().toString();
		String randomName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
		
//		book.setPhotos(Base64.getEncoder().encodeToString(randomName.getBytes()));
		book.setPhotos(randomName);

		Book book_save = this.bookservice.saveBook(book);
		
		// new folder create
//		String uploadDir = path + book_save.getBook_id();

		this.saveFile(path, randomName, photo);
			
		model.addAttribute("books", bookservice.getAllBooks());

		return "redirect:/admin/book";
	}

	public void saveFile(String path, String fileName, MultipartFile file) throws IOException {

		
		File f = new File(path);
		
		if(!f.exists()) {
			f.mkdir();
		}
		
//		if (!Files.exists(uploadPath)) {
//			Files.createDirectories(uploadPath);
//		}

		try {
//			Path uploadPath = Paths.get(path);
//			InputStream input = file.getInputStream();
//			Path filePath = uploadPath.resolve(fileName);
//			System.out.println("file path :::: : : : :: : :: "+ filePath);
			
			Files.copy(file.getInputStream(), Paths.get(path+File.separator+fileName), StandardCopyOption.REPLACE_EXISTING);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/add")
	public String showCreateForm(Book book, Model model) {

		model.addAttribute("category", categoryService.getAllCategory());
		model.addAttribute("authors", authorService.getAllAuthors());

		return "admin/book_add";
	}

	@GetMapping("/book_edit/{book_id}")
	public String editBook(@PathVariable int book_id, Model model) {

		BookDto book = bookservice.getBookById(book_id);
		model.addAttribute("book", book);

		return "admin/book_edit";
	}

	@PostMapping("/book_update/{book_id}")
	public String updateBook(@ModelAttribute BookDto user, @PathVariable int book_id) {

		bookservice.updateBook(user, book_id);

		return "redirect:/admin/book";
	}

	@GetMapping("/book_delete/{book_id}")
	public String deleteBook(@PathVariable int book_id) {
		bookservice.deleteBook(book_id);

		return "redirect:/admin/book";
	}

}
