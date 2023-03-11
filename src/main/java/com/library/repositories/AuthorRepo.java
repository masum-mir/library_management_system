package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Long>{

}
