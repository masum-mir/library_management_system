package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.dto.CategoryDto;
import com.library.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	
}
