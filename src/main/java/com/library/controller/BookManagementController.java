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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.dto.BookDto;
import com.library.entity.Author;
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
	
	@GetMapping("/admin/issue_book")
	public String issueBook(Model m) {
		
		List<BookManagement> bookManagement = bookManagementService.getBookInfo();
		
		m.addAttribute("bookManagement",bookManagement);
		
		return "admin/book_issue";
	}
	
	
	
	@GetMapping("/admin/book_details/{id}")
	public String getBookInfo(@PathVariable Long id, Model model) {
		
		Book book = bookService.getBookById(id);
		
		model.addAttribute("books",book);
		
		return "admin/book_details";
	}
	
	@PostMapping("/admin/save_book_borrow")
	public String borrowBook(@ModelAttribute BookManagement book) {
		book.setType("G");
		bookManagementService.saveBookManagement(book);
		
		return "redirect:/admin/dashboard";
	}
	
	@PostMapping("/admin/save_book_return/{id}")
	public String returnBook(@ModelAttribute BookManagement book, @PathVariable int id) {
		
		book.setType("R");
		bookManagementService.updateBookManagement(book, id);
		
		return "redirect:/admin/dashboard";
	}
	
	// id pass modal
	@ResponseBody
	@RequestMapping("/admin/book_detail/{id}")
	public Book car(@PathVariable("id") Long id, Model model) {
	    Book book = null;
	    book = bookService.getBookById(id);
	    return book;
	}
	
	
}
