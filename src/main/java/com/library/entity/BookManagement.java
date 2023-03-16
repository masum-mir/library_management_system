package com.library.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.library.enums.BookMovementStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="book_managements")
public class BookManagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int book_management_id;
	
	@Enumerated(EnumType.STRING)
	private BookMovementStatus book_movement_status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date book_given_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date book_return_date;
	private String book_condition_during_given;
	private String book_condition_during_return;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customers customer_details;
	
	@ManyToOne
	private Book books;
	
	public int getBook_management_id() {
		return book_management_id;
	}

	public void setBook_management_id(int book_management_id) {
		this.book_management_id = book_management_id;
	}

	public BookMovementStatus getBook_movement_status() {
		return book_movement_status;
	}

	public void setBook_movement_status(BookMovementStatus book_movement_status) {
		this.book_movement_status = book_movement_status;
	}

	public Date getBook_given_date() {
		return book_given_date;
	}

	public void setBook_given_date(Date book_given_date) {
		this.book_given_date = book_given_date;
	}

	public Date getBook_return_date() {
		return book_return_date;
	}

	public void setBook_return_date(Date book_return_date) {
		this.book_return_date = book_return_date;
	}

	public String getBook_condition_during_given() {
		return book_condition_during_given;
	}

	public void setBook_condition_during_given(String book_condition_during_given) {
		this.book_condition_during_given = book_condition_during_given;
	}

	public String getBook_condition_during_return() {
		return book_condition_during_return;
	}

	public void setBook_condition_during_return(String book_condition_during_return) {
		this.book_condition_during_return = book_condition_during_return;
	}

	public Book getBooks() {
		return books;
	}

	public void setBooks(Book books) {
		this.books = books;
	}

	public Customers getCustomer_details() {
		return customer_details;
	}

	public void setCustomer_details(Customers customer_details) {
		this.customer_details = customer_details;
	}
	
}
