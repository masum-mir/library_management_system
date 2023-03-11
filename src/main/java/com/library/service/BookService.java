package com.library.service;

import java.util.List;

import com.library.dto.BookDto;
import com.library.entity.Book;

public interface BookService {
	
	public List<BookDto> getAllBooks();
	
	public BookDto getBookById(int book_id);
	
	public BookDto saveBook(BookDto book);
	
	public void deleteBook(int book_id);
	
	public BookDto updateBook(BookDto book, int book_id);

}
