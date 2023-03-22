package com.library;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class LibraryManagementApplication {
	
	public static boolean isFactorOnly235(int num) {
		while (num % 2 == 0) {
			num /= 2;
		} 
		while (num % 3 == 0) {
			num /= 3;
		}
		while (num % 5 == 0) {
			num /= 5;
		}
		return num == 1;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);

		System.out.println("Hello world");

//		int count = 0;
//		int num = 2;
//
//		long startTime = System.currentTimeMillis();
//
//		while (count <= 1500) {
//			if (isFactorOnly235(num)) {
//				System.out.print(num + " ");
//				count++;
//			}
//			num++;
//		}
//
//		long endTime = System.currentTimeMillis();
//
//		System.out.println("\nTime taken: " + (endTime - startTime) + " ms");
//	



	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	/*
	 * @Bean public CommandLineRunner initialCreate(BookService service) {
	 * 
	 * return (args) -> {
	 * 
	 * Book book = new Book(); book.setBook_name("Test isbn");
	 * book.setTitle("Test name"); book.setTitle("Test serial name");
	 * book.setVersion_no("3");
	 * 
	 * Category category = new Category();
	 * category.setCategory_name("Test category name");
	 * 
	 * book.addCategories(category);
	 * 
	 * service.saveBook(book);
	 * 
	 * }; }
	 */



}
