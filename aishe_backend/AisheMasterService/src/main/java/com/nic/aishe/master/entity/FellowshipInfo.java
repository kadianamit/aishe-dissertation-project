package com.nic.aishe.master.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="fellowship")
public class FellowshipInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Transient
	private Integer surveyYear;
	@Transient
	private Integer aisheCode;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="count_by_category_id")
	private PersonCategoryCount personCategoryCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PersonCategoryCount getPersonCategoryCount() {
		return personCategoryCount;
	}

	public void setPersonCategoryCount(PersonCategoryCount personCategoryCount) {
		this.personCategoryCount = personCategoryCount;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

	public Integer getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(Integer aisheCode) {
		this.aisheCode = aisheCode;
	}

	

	
}