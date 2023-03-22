package com.library.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.library.dto.BookDto;
import com.library.entity.Book;
import com.library.entity.BookManagement;
import com.library.entity.User;
import com.library.repositories.UserRepo;
import com.library.service.AuthorService;
import com.library.service.BookManagementService;
import com.library.service.BookService;
import com.library.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class BookController {

	@Value("${project.image}")
	private String path;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BookService bookservice;
	
	@Autowired
	BookManagementService bookManagementService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	AuthorService authorService;
	
//	private String path = "E:\\Java Project\\library_management_system\\images";
//	private String path = new ClassPathResource("static/images/").getFile().getAbsolutePath();
	
	public BookController() throws IOException {
		
	}
	
	@ModelAttribute
	public void addCommonData(Model m, Principal principal) {
		String username = principal.getName();
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String name = authentication.getName();
		
		User user = userRepo.getUserByUserName(username);
		m.addAttribute("user", user);
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
			return "redirect:/admin/add";
		}
		
		if(photo.isEmpty()) {
			return "redirect:/admin/add";
		}

		String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
		
		String randomId= UUID.randomUUID().toString();
		String randomName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
		
//		book.setPhotos(photo.getOriginalFilename());
//		book.setPhotos(Base64.getEncoder().encodeToString(randomName.getBytes()));
		book.setPhotos(randomName);
		
		// url create
		String downloadUrl = path+File.separator +randomName;
		System.out.println("downloadUrl : ::::::::::: : : : : : :: :::"+downloadUrl);
		
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

	@GetMapping("/book_edit/{bookId}")
	public String editBook(@PathVariable long bookId, Model model) {

		Book book = bookservice.getBookById(bookId);
		model.addAttribute("book", book);

		return "admin/book_edit";
	}

	@PostMapping("/book_update/{bookId}")
	public String updateBook(@ModelAttribute Book book, @PathVariable long bookId) {

		bookservice.updateBook(book, bookId);

		return "redirect:/admin/book";
	}

	@GetMapping("/book_delete/{bookId}")
	public String deleteBook(@PathVariable long bookId) {
		bookservice.deleteBook(bookId);

		return "redirect:/admin/book";
	}
	

	@RequestMapping("/searchBook/{keyword}")
	public String SearchBooks(@PathVariable String keyword, Model model, Book book) {
		if(keyword != null) {
			final List<Book> books = bookservice.searchBooks(keyword);
			model.addAttribute("books",books);
		}
		
//		model.addAttribute("keyword", keyword);
		return "admin/dashboard";
	}

//	@GetMapping("/book_details/{id}")
//	public String getBookDetails(@PathVariable int id, Model model) {
//		BookManagement b = new BookManagement();
//		Book bk  = new Book();
//		
//		BookDto book = bookservice.getBookById(id);
////		BookManagement books = bookManagementService.findByBookManagementId(id);
//		model.addAttribute("book",book);
//		
//		return "admin/book_details";
//	}

}
