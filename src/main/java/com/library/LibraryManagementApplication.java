package com.library;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.library.entity.Book;
import com.library.entity.Category;
import com.library.service.BookService;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
		
		System.out.println("Hello world");
		
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	/*
	@Bean
	public CommandLineRunner initialCreate(BookService service) {
		
		return (args) -> {
			
			Book book = new Book();
			book.setBook_name("Test isbn");
			book.setTitle("Test name");
			book.setTitle("Test serial name");
			book.setVersion_no("3");
			
			Category category = new Category();
			category.setCategory_name("Test category name");

			book.addCategories(category);
			
			service.saveBook(book);
			
		};
	}*/

}
