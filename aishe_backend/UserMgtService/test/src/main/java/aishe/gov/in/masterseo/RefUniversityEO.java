package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



public class RefUniversityEO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "survey_year")
	private Integer surveyYear;
	@Column(name = "name")
	private String name;
	@ManyToOne
	@JoinColumn(name = "type_id", insertable = false, updatable = false)
	private RefUniversityType type;
	@ManyToOne
	@JoinColumn(name = "district_code", insertable = false, updatable = false)
	private RefDistrict district;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RefDistrict getDistrict() {
		return district;
	}
	public void setDistrict(RefDistrict district) {
		this.district = district;
	}
	public RefUniversityType getType() {
		return type;
	}
	public void setType(RefUniversityType type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	
}
