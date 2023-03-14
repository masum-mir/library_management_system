package com.library.service;

import java.util.List;

import com.library.entity.Author;

public interface AuthorService {
	
	public List<Author> getAllAuthors();
	public Author getAuthorById(long id);
	public Author saveAuthor(Author author);
	public Author updateAuthor(Author author, long id);
	public void deleteAuthor(long id);
	
}
