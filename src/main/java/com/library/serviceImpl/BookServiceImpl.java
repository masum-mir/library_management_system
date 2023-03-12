package com.library.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BookDto;
import com.library.entity.Book;
import com.library.entity.Category;
import com.library.exception.ResourceNotFoundException;
import com.library.repositories.BookRepo;
import com.library.repositories.CategoryRepo;
import com.library.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public List<Book> getAllBooks() {
		
		List<Book> books = bookRepo.findAll();
//		List<BookDto> bookDto = books.stream().map(e -> this.modelMapper.map(e, BookDto.class)).collect(Collectors.toList());
		
		return books;
	}

	@Override
	public BookDto getBookById(int id) {
		
		Book book = bookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID : "+id));
	
		return this.modelMapper.map(book, BookDto.class);
	}


	@Override
	public Book saveBook(Book book) {
		
		return this.bookRepo.save(book);
	}

	@Override
	public void deleteBook(int id) {
		bookRepo.deleteById(id);
		
	}

	@Override
	public BookDto updateBook(BookDto bookDto, int id) {
		Book book = bookRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Book id not found:: "+id));
				
		book.setBook_name(bookDto.getBook_name());
		book.setTitle(bookDto.getTitle());
		book.setDescription(bookDto.getDescription());
		book.setVersion_no(bookDto.getVersion_no());
//		book.setPhotos(bookDto.ge);
		
		Book updateBook = bookRepo.save(book);
		BookDto updateBookDto = this.modelMapper.map(updateBook, BookDto.class);
		
		return updateBookDto;
	}
	
	@Override
	public List<Book> searchBooks(String keyword) {
		
//		if(keyword != null) {
//			return this.bookRepo.search(keyword);
//		}
		
		return bookRepo.findAll();
	}
	
	
	// upload image ...!!!
	@Override
	public String uploadImage(String path, MultipartFile file) {
		
		// file name
		String fileName = file.getOriginalFilename();
		
		String randomId = UUID.randomUUID().toString();
		String randomName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
		
		// full path
		String filePath = path + File.separator + randomName;
		
		File f = new File(path);
		
		if(!f.exists()) {
			f.mkdir();
		}
		
		// files copy
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
	
	
	// serve image ...!!!
	
	public InputStream getResource(String path, String fileName) {
		
		String fullPath = path + File.separator + fileName;
		
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return inputStream;
	}

}
