package com.library.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BookDto;
import com.library.entity.Book;

public interface BookService {
	
	public List<Book> getAllBooks();
	
	public Book getBookById(long book_id);
	
	public Book saveBook(Book book);
	
	public void deleteBook(long book_id);
	
	public Book updateBook(Book book, long book_id);
	
	public List<Book> searchBooks(String keywords);
	
	public String uploadImage(String path, MultipartFile file);
	
	public InputStream getResource(String path, String fileName);

}
