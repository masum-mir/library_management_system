package com.library.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Author;
import com.library.repositories.AuthorRepo;
import com.library.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	AuthorRepo authorRepo;

	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors = authorRepo.findAll();
		
		return authors;
	}

	@Override
	public Author updateAuthor(Author author) {
		return authorRepo.save(author);
	}

	@Override
	public void deleteAuthor(long id) {
		authorRepo.deleteById(id);
	}

	@Override
	public Author getAuthorById(long id) {
		
		Optional<Author> author = authorRepo.findById(id);
		
		if(author.isPresent()) {
			return author.get();
		} else {
			return null;
		}

	}
	
	@Override
	public Author saveAuthor(Author author) {
		Author save = authorRepo.save(author);
		return save;
	}

}
