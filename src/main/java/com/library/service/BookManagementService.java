package com.library.service;

import java.util.List;

import com.library.entity.BookManagement;

public interface BookManagementService {

	List<BookManagement> getBookInfo();
	
	BookManagement saveBookManagement(BookManagement book);
	
	BookManagement findByBookManagementId(int id);
	
}
