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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BookDto;
import com.library.entity.User;
import com.library.repositories.UserRepo;
import com.library.service.BookService;
import com.library.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class BookController {


	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BookService bookservice;

	@Autowired
	CategoryService categoryService;
	
	@ModelAttribute
	public void addCommonData(Model m, Principal principal) {
		String username = principal.getName();
		System.out.println("username :::::::::::::::" + username);
		User user = userRepo.getUserByUserName(username);
		System.out.println("user :::::::::::::::" + user.getName());
		m.addAttribute("user", user);
	}

	@GetMapping("/book")
	public String getAllBooks(Model model) {

		List<BookDto> books = this.bookservice.getAllBooks();

		model.addAttribute("book", books);

		return "admin/book_list";
	}

	@PostMapping("/book_save")
	public String saveBook(BookDto bookDto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "admin/book_add";
		}

		this.bookservice.saveBook(bookDto);
		model.addAttribute("books", bookservice.getAllBooks());

		return "redirect:/admin/book";
	}

	public void saveFile(String uploadDir, String fileName, MultipartFile file) throws IOException {
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Could not save image file: " + fileName, e);
		}
	}

	@GetMapping("/add")
	public String showCreateForm(BookDto book, Model model) {

		model.addAttribute("category", categoryService.getAllCategory());

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

	/*
	 * @GetMapping("/book") public String bookAdd(Model model) {
	 * 
	 * List<Book> b = this.service.getAllBooks();
	 * 
	 * model.addAttribute("book", b);
	 * 
	 * return "admin/book_list"; }
	 */
	/*
	 * @PostMapping("/book_save") public String bookSave(@ModelAttribute Book book)
	 * throws IOException {
	 * 
	 * // if(file.isEmpty()) { // System.out.println("File is impty"); // } else {
	 * // book.setPhotos(file.getOriginalFilename()); // // File saveFile = new
	 * ClassPathResource("static").getFile(); // // Path path =
	 * Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename(
	 * )); // // Files.copy(file.getInputStream(), path,
	 * StandardCopyOption.REPLACE_EXISTING); // //
	 * System.out.println("Uploaded image"); // }
	 * 
	 * 
	 * Book b = this.service.saveBook(book);
	 * 
	 * return "redirect:/admin/book"; }
	 * 
	 * public void saveFile(String uploadDir, String fileName, MultipartFile file)
	 * throws IOException { Path uploadPath = Paths.get(uploadDir);
	 * 
	 * if(!Files.exists(uploadPath)) { Files.createDirectories(uploadPath); }
	 * 
	 * try (InputStream inputStream = file.getInputStream()) { Path filePath =
	 * uploadPath.resolve(fileName); Files.copy(inputStream, filePath,
	 * StandardCopyOption.REPLACE_EXISTING); } catch (Exception e) {
	 * e.printStackTrace(); throw new IOException("Could not save image file: "+
	 * fileName, e); } }
	 * 
	 * @GetMapping("/add") public String showCreateForm(Book book, Model model) {
	 * 
	 * model.addAttribute("category", categoryService.getAllCategory());
	 * 
	 * return "book_add"; }
	 * 
	 * @ModelAttribute("cate") public List<Category> getCategories(Model m) { //
	 * List<Category> li = new ArrayList<>(); // li.add(new Category(1,"A")); //
	 * li.add(new Category(1,"B")); m.addAttribute("cate",
	 * categoryService.getAllCategory()); return null; }
	 * 
	 * @GetMapping("/book_edit/{book_id}") public String editBook(@PathVariable int
	 * book_id, Model model) {
	 * 
	 * Book book = service.getBookById(book_id); model.addAttribute("book",book);
	 * 
	 * return "book_edit"; }
	 * 
	 * @PostMapping("/book_update") public String updateBook(@ModelAttribute Book
	 * user) {
	 * 
	 * service.saveBook(user);
	 * 
	 * return "redirect:/admin/book"; }
	 * 
	 * @GetMapping("/book_delete/{book_id}") public String deleteBook(@PathVariable
	 * int book_id) { service.deleteBook(book_id);
	 * 
	 * return "redirect:/admin/book"; }
	 */

}
