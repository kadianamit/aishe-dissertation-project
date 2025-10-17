package com.nic.aishe.master.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InstituteHostelInfo {
	
	@Id
	private String aisheCode;
	private Integer hostelId;
	
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
	public Integer getHostelId() {
		return hostelId;
	}
	public void setHostelId(Integer hostelId) {
		this.hostelId = hostelId;
	}
	
	
	 
	
	

}
