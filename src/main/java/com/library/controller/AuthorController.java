package com.library.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.entity.Author;
import com.library.entity.User;
import com.library.repositories.UserRepo;
import com.library.service.AuthorService;

@Controller
@RequestMapping("/admin")
public class AuthorController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AuthorService authorService;
	
	@ModelAttribute
	public void addCommonData(Model m, Principal principal) {
		String username = principal.getName();
		System.out.println("username :::::::::::::::" + username);
		User user = userRepo.getUserByUserName(username);
		System.out.println("user :::::::::::::::" + user);
		m.addAttribute("user", user);
	}
	
	// author api
	
	@GetMapping("/authors")
	public String authorList(Model model) {
	
		List<Author> authors = authorService.getAllAuthors();
		model.addAttribute("author",authors);
		
		return "admin/author_list";
	}
	
	@GetMapping("/author_add")
	public String authorAdd() {
		return "admin/author_add";
	}
	
	@PostMapping("/author_save")
	public String saveAuthor(@ModelAttribute Author author) {
		
		authorService.saveAuthor(author);
		
		return "redirect:/admin/authors";
	}
	
	@GetMapping("/author_edit/{id}")
	public String editAuthor(@PathVariable long id, Model model) {
		Author author = authorService.getAuthorById(id);
		model.addAttribute("author",author);
		return "/admin/author_edit";
	}
	
	@PostMapping("/author_update")
	public String updateAuthor(@ModelAttribute Author author) {
		
		authorService.updateAuthor(author);
		
		return "redirect:/admin/authors";
	}
	
	@GetMapping("/author_delete/{id}")
	public String authorDelete(@PathVariable long id) {
		authorService.deleteAuthor(id);
		
		return "redirect:/admin/authors";
	}
	

}
