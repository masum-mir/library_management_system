package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Customers;

public interface CustomerRepo extends JpaRepository<Customers, Integer> {

}
