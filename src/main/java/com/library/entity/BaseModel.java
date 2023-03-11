package com.library.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

abstract class BaseModel {
	
	@Id
	@Column(name="id", updatable = false)
	private int id;
	private String createdBy;
	private Timestamp createdOn;
	private String updatedBy;
	private Timestamp updatedOn;
	private boolean isActive;
	
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	

}
