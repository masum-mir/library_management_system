package com.library.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.dto.BookDto;
import com.library.entity.BookManagement;
import com.library.repositories.BookManagementRepo;
import com.library.service.BookManagementService;
import com.library.service.BookService;

@Service
public class BookManagementServiceImpl implements BookManagementService{

	@Autowired
	BookManagementRepo bookManagementRepo;

	@Override
	public List<BookManagement> getBookInfo() {
		
		return bookManagementRepo.findAll();
	}

	@Override
	public BookManagement saveBookManagement(BookManagement book) {
		
		return bookManagementRepo.save(book);
	}

	@Override
	public BookManagement findByBookManagementId(int id) {
		
		BookManagement book = bookManagementRepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("Book Id Not Found:: "+ id));
		
		return book;
	}
	
	
}
