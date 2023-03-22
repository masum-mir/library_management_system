package com.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
	
	@Query("SELECT b FROM Book b WHERE b.bookName LIKE :key")
	List<Book> findByBookName(@Param("key") String keyword);
	
//	public Book findByNameContaining(String keywords);
	
}
