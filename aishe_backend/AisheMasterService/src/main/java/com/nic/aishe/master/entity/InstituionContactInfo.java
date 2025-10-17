package com.nic.aishe.master.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InstituionContactInfo {
	
	@Id
	private String aisheCode;
	private Integer personId;
		
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	
	

}
