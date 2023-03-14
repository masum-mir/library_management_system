package com.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_details")
public class Customers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customer_id;
	private String customer_name;
	private String customer_address;
	private String customer_number;
	private String customer_email;
	private String university_name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private RegistrationDetails registration_details;
	
	@OneToMany(mappedBy = "customer_details")
	@JsonBackReference
	private List<BookManagement> bookManagement;

	
	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_number() {
		return customer_number;
	}

	public void setCustomer_number(String customer_number) {
		this.customer_number = customer_number;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getUniversity_name() {
		return university_name;
	}

	public void setUniversity_name(String university_name) {
		this.university_name = university_name;
	}

	public RegistrationDetails getRegistration_details() {
		return registration_details;
	}

	public void setRegistration_details(RegistrationDetails registration_details) {
		this.registration_details = registration_details;
	}

	public List<BookManagement> getBookManagement() {
		return bookManagement;
	}

	public void setBookManagement(List<BookManagement> bookManagement) {
		this.bookManagement = bookManagement;
	}

}
