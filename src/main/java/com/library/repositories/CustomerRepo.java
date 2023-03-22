package com.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.entity.Customers;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Long> {

	@Query("SELECT s FROM Customers s WHERE s.customerId like :key")
	List<Customers> findByCustomerIdContaining(@Param("key") long customerId);
	
}
