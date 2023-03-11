package com.library.payloads;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class AppResponse<T> {

	private Date timestamp;
	private String details;
	private HttpStatus status;
	private String message;
	private String error;
	private String path;
	private T body;
	private T header;

	private AppResponse() {

	}

	private AppResponse(HttpStatus status) {
		this.status = status;
		this.timestamp = new Date();
	}

	public AppResponse message(String message) {
		this.message = message;
		return this;
	}

	public AppResponse body(T body) {
		this.body = body;
		return this;
	}

	public AppResponse header(T header) {
		this.header = header;
		return this;
	}

	public AppResponse build(HttpStatus status) {
		return new AppResponse(status);
	}

	public Integer getStatus() {
		return status.value();
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getDetails() {
		return details;
	}

	public String getError() {
		return error;
	}

	public String getPath() {
		return path;
	}

	public T getBody() {
		return body;
	}

	public T getHeader() {
		return header;
	}

}
