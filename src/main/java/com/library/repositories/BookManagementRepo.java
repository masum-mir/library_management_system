package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.BookManagement;

public interface BookManagementRepo extends JpaRepository<BookManagement, Integer> {

}
