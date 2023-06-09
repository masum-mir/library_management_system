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
		
		List<Book> books = (List<Book>) bookRepo.findAll();
//		List<BookDto> bookDto = books.stream().map(e -> this.modelMapper.map(e, BookDto.class)).collect(Collectors.toList());
		
		return books;
	}

	@Override
	public Book getBookById(long id) {
		
		Book book = bookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID : "+id));
	
		return book;
	}


	@Override
	public Book saveBook(Book book) {
		
		return this.bookRepo.save(book);
	}

	@Override
	public void deleteBook(long id) {
		bookRepo.deleteById(id);
		
	}

	@Override
	public Book updateBook(Book books, long id) {
		Book book = bookRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Book id not found:: "+id));
				
		book.setBookName(books.getBookName());
		book.setTitle(books.getTitle());
		book.setDescription(books.getDescription());
		book.setVersion_no(books.getVersion_no());
//		book.setPhotos(books.getPhotos());
		
		Book updateBook = bookRepo.save(book);
//		BookDto updateBookDto = this.modelMapper.map(updateBook, BookDto.class);
		
		return updateBook;
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
	
	@Override
	public List<Book> searchBooks(String keywords) {
		
		if(keywords != null) {
			return this.bookRepo.findByBookName("%"+keywords+"%");
		}
		
		return this.bookRepo.findAll();
	}


}
