package com.library.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.RegistrationDetails;
import com.library.repositories.RegistrationRepo;
import com.library.service.RegistrationDetailsService;

@Service
public class RegistrationDetailsServiceImpl implements RegistrationDetailsService {

	@Autowired
	RegistrationRepo registrationRepo;
	
	@Override
	public List<RegistrationDetails> getRegistrationDetails() {

		return registrationRepo.findAll();
	}

	@Override
	public RegistrationDetails saveRegistration(RegistrationDetails registrationDetails) {

		return registrationRepo.save(registrationDetails);
	}

}
