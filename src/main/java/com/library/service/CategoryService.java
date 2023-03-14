package com.library.service;

import java.util.List;
import java.util.Optional;

import com.library.dto.CategoryDto;
import com.library.entity.Category;

public interface CategoryService {
	
	public CategoryDto getCategoryById(int id);
	
	public List<CategoryDto> getAllCategory(); 
	
	public CategoryDto saveCategory(CategoryDto category);
	
	public void deleteCategoryById(int id);
	
	public CategoryDto updateCategory(CategoryDto category, int id);
	

}
