package com.library.service;

import java.util.List;

import com.library.entity.RegistrationDetails;

public interface RegistrationDetailsService {
	
	List<RegistrationDetails> getRegistrationDetails();
	
	RegistrationDetails saveRegistration(RegistrationDetails registrationDetails);

}
