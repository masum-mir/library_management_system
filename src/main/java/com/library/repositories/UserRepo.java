package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.library.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
	
	@Query("select u from User u where u.email = :email")
	public User getUserByUserName(@Param("email") String email);
	
}
