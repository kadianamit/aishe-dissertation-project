package com.nic.aishe.master.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InstituteAddressInfo {
	
	@Id
	private String aisheCode;
	private Integer addressId;
	
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
	 
	
	

}
