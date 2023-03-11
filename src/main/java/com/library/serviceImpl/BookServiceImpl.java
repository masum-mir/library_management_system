package com.library.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
	public List<BookDto> getAllBooks() {
		
		List<Book> books = bookRepo.findAll();
		List<BookDto> bookDto = books.stream().map(e -> this.modelMapper.map(e, BookDto.class)).collect(Collectors.toList());
		
		return bookDto;
	}

	@Override
	public BookDto getBookById(int id) {
		
		Book book = bookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID : "+id));
	
		return this.modelMapper.map(book, BookDto.class);
	}


	@Override
	public BookDto saveBook(BookDto bookDto) {
//		int i = Integer.parseInt(id);
//		Category category = categoryRepo.findById(i).orElseThrow(() -> new UsernameNotFoundException("Category not found :: "+id));
		Book book = this.modelMapper.map(bookDto, Book.class);
//		book.setCategories(category);
		Book bookSave = this.bookRepo.save(book); 
		
		return this.modelMapper.map(bookSave, BookDto.class);
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

}
