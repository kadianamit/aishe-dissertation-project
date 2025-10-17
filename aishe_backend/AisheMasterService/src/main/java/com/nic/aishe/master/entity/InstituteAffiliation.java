package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="institution_affiliation_details",schema = "institutionprofile")
public class InstituteAffiliation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="aishe_code")
	private String aisheCode;
	
	@Column(name="university_id")
	private Integer universityId;
	
	@Column(name="year_of_affiliation")
	private Integer affiliationYear;

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

	public Integer getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Integer universityId) {
		this.universityId = universityId;
	}

	public Integer getAffiliationYear() {
		return affiliationYear;
	}

	public void setAffiliationYear(Integer affiliationYear) {
		this.affiliationYear = affiliationYear;
	}

}
