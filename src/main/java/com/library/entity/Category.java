package com.library.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseModel{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cat_id;
	private String category_name;
	
	/*
	 * // @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
	 * CascadeType.MERGE}, mappedBy = "categories")
	 * 
	 * @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	 * 
	 * @JsonIgnore private Set<Book> books = new HashSet<>();
	 */
	

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "categories")
	@JsonIgnore
	private Set<Book> books = new HashSet<Book>();

	public Category(String category_name) {
		this.category_name = category_name;
	}
	
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}	
	public String getCategory_name() {
		return category_name;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public Category(int cat_id, String category_name) {
		this.cat_id = cat_id;
		this.category_name = category_name;
	}
	public Category() {		
	}
	
}
