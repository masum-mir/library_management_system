package com.library.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.dto.CategoryDto;
import com.library.entity.Category;
import com.library.repositories.CategoryRepo;
import com.library.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> category = categoryRepo.findAll();
		
		List<CategoryDto> categoryDto = category.stream().map(e-> this.modelMapper.map(e, CategoryDto.class)).collect(Collectors.toList());
		
		return categoryDto;
	}
	
	@Override
	public CategoryDto getCategoryById(int id) {
		
		Category category = categoryRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Category not found :" +id));
		/*
		 * if(c.isPresent()) { return c.get(); }
		 */

		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category saveCategory = categoryRepo.save(category);
		
		return this.modelMapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategoryById(int id) {
		categoryRepo.deleteById(id);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
		
		Category category = categoryRepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("Category Id not found :: "+id));
		
		category.setCategory_name(categoryDto.getCategory_name());
		
		Category saveCategory = categoryRepo.save(category);
		CategoryDto saveCategoryDto = this.modelMapper.map(saveCategory, CategoryDto.class);
		
		return saveCategoryDto;
	}

}
