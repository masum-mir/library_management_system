package com.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="registration_details")
public class RegistrationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int registration_details_id;
	private String subscription_fees;
	private String subscription_period;
	private String subscription_validate;
	private Date subscription_data;
	private String registration_id;
	
//	@OneToOne(mappedBy = "registration_details")
//	@JsonBackReference
//	private Customers customers;

	public int getRegistration_details_id() {
		return registration_details_id;
	}

	public void setRegistration_details_id(int registration_details_id) {
		this.registration_details_id = registration_details_id;
	} 

	public String getSubscription_fees() {
		return subscription_fees;
	}

	public void setSubscription_fees(String subscription_fees) {
		this.subscription_fees = subscription_fees;
	}

	public String getSubscription_period() {
		return subscription_period;
	}

	public void setSubscription_period(String subscription_period) {
		this.subscription_period = subscription_period;
	}

	public String getSubscription_validate() {
		return subscription_validate;
	}

	public void setSubscription_validate(String subscription_validate) {
		this.subscription_validate = subscription_validate;
	}


	public Date getSubscription_data() {
		return subscription_data;
	}

	public void setSubscription_data(Date subscription_data) {
		this.subscription_data = subscription_data;
	}

	public String getRegistration_id() {
		return registration_id;
	}

	public void setRegistration_id(String registration_id) {
		this.registration_id = registration_id;
	}

//	public Customers getCustomers() {
//		return customers;
//	}
//
//	public void setCustomers(Customers customers) {
//		this.customers = customers;
//	}

}
