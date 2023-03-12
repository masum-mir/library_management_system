package com.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
	
//	@Query("SELECT b FROM Book WHERE b.book_name LIKE %?1%")
//	public List<Book> search(String keyword);
	
}
