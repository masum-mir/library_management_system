package com.library.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Customers;
import com.library.repositories.CustomerRepo;
import com.library.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public List<Customers> getCustomer() {

		return customerRepo.findAll();
	}

	@Override
	public Customers createCustomer(Customers customers) {
		
//		customers.setSubcription_date(new Date());

		return customerRepo.save(customers);
	}

}
