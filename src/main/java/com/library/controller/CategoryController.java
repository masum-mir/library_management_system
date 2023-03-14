package com.library.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.dto.CategoryDto;
import com.library.entity.Category;
import com.library.entity.User;
import com.library.repositories.UserRepo;
import com.library.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CategoryService categoryService;
	
	@ModelAttribute
	public void addCommonData(Model m, Principal principal) {
		String username = principal.getName();
		User user = userRepo.getUserByUserName(username);
		m.addAttribute("user", user);
	}

	// category api

//	@ModelAttribute("cate")
//	public List<Category> getCategories(Model m) {
////		List<Category> li = new ArrayList<>();
////		li.add(new Category(1,"A"));
////		li.add(new Category(1,"B"));
//		m.addAttribute("cate", categoryService.getAllCategory());
//		return null;
//	}

	@GetMapping("/category")
	public String categoryList(Model model) {

		List<CategoryDto> category = this.categoryService.getAllCategory();
		model.addAttribute("category", category);

		return "admin/category_list";
	}

	@GetMapping("/category_add")
	public String categoryAdd() {
		return "admin/category_add";
	}

	@PostMapping("/category_save")
	public String saveCategory(@ModelAttribute("category") CategoryDto categoryDto) {

		this.categoryService.saveCategory(categoryDto);

		return "redirect:/admin/category";
	}

	@GetMapping("/category_edit/{id}")
	public String editCategory(@PathVariable int id, Model model) {

		CategoryDto category = categoryService.getCategoryById(id);
		model.addAttribute("category", category);

		return "admin/category_edit";
	}

	@PostMapping("/category_update/{id}")
	public String updateCategory(@ModelAttribute CategoryDto categoryDto, @PathVariable int id) {

		categoryService.updateCategory(categoryDto,id);

		return "redirect:/admin/category";
	}

	@GetMapping("/category_delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.deleteCategoryById(id);

		return "redirect:/admin/category";
	}

}
