package com.library.service;

import java.util.List;

import com.library.entity.Customers;

public interface CustomerService {

	List<Customers> getCustomer();
	
	Customers createCustomer(Customers customers);
	
	List<Customers> findByCustomerIdContaining(long id);
	
}
