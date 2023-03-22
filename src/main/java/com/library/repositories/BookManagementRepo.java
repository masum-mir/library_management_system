package com.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.entity.BookManagement;

public interface BookManagementRepo extends JpaRepository<BookManagement, Integer> {
	
	@Query("SELECT s FROM BookManagement s WHERE s.type='G'")
	List<BookManagement> findByType();
	
}
