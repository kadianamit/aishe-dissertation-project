package com.nic.aishe.master.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InstituteStatutoryBody {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String aisheCode;
	private Integer statutoryBodyId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
	public Integer getStatutoryBodyId() {
		return statutoryBodyId;
	}
	public void setStatutoryBodyId(Integer statutoryBodyId) {
		this.statutoryBodyId = statutoryBodyId;
	}
	
	
}
