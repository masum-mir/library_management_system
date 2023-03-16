package com.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "registration_details")
public class RegistrationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int registration_details_id;
	private String subscription_fees;
	private String subscription_period;
	private String subscription_validate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date subscription_date;
	private String registration_id;

	@OneToOne(mappedBy = "registration_details")
	@JsonBackReference
	private Customers customers;

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

	public Date getSubscription_date() {
		return subscription_date;
	}

	public void setSubscription_data(Date subscription_date) {
		this.subscription_date = subscription_date;
	}

	public String getRegistration_id() {
		return registration_id;
	}

	public void setRegistration_id(String registration_id) {
		this.registration_id = registration_id;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

}
