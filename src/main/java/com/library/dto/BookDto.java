package com.library.dto;

public class BookDto {

	private int book_id;
	private String book_name;
	private String title;
	private String version_no;
	private String description;
	private String photos;
	private CategoryDto categories;
	private AuthorDto authors;

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVersion_no() {
		return version_no;
	}

	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryDto getCategories() {
		return categories;
	}

	public void setCategories(CategoryDto categories) {
		this.categories = categories;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public AuthorDto getAuthors() {
		return authors;
	}

	public void setAuthors(AuthorDto authors) {
		this.authors = authors;
	}
	

}
