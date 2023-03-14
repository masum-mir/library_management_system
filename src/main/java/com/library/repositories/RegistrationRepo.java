package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.RegistrationDetails;

public interface RegistrationRepo extends JpaRepository<RegistrationDetails, Integer>{

}
