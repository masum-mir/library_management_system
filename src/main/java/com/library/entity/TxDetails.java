package com.library.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tx_details")
public class TxDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long txId;
	
	private boolean isAvailable = Boolean.FALSE;

	@OneToOne(mappedBy = "tx_details")
	@JsonBackReference
	private BookManagement book_managements;
	
	@OneToOne
	@JoinColumn(name="customerId")
	private Customers customer_details;
	

	@OneToOne
	@JoinColumn(name="bookId")
	private Book books;


	public long getTxId() {
		return txId;
	}


	public void setTxId(long txId) {
		this.txId = txId;
	}


	public boolean isAvailable() {
		return isAvailable;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	public BookManagement getBook_managements() {
		return book_managements;
	}


	public void setBook_managements(BookManagement book_managements) {
		this.book_managements = book_managements;
	}


	public Customers getCustomer_details() {
		return customer_details;
	}


	public void setCustomer_details(Customers customer_details) {
		this.customer_details = customer_details;
	}


	public Book getBooks() {
		return books;
	}


	public void setBooks(Book books) {
		this.books = books;
	}
	
	
}
