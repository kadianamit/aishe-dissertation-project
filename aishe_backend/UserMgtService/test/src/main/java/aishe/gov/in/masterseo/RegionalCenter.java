package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "regional_center")
public class RegionalCenter implements Serializable{
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "survey_year")
	private Integer surveyYear;
	@Column(name = "university_id")
	private String universityId;
	@Column(name = "no_of_study_centers")
	private Integer noOfStudyCenter;	
	@Column(name = "district_code")
	private String districtCode;
	@Column(name = "state_code")
	private String stateCode;
	@Transient
	private Boolean nameOfRegionalCenter;
	@Transient
	private Boolean programmeThroughDistanceMode;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public Integer getNoOfStudyCenter() {
		return noOfStudyCenter;
	}
	public void setNoOfStudyCenter(Integer noOfStudyCenter) {
		this.noOfStudyCenter = noOfStudyCenter;
	}	
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public Boolean getNameOfRegionalCenter() {
		return nameOfRegionalCenter;
	}
	public void setNameOfRegionalCenter(Boolean nameOfRegionalCenter) {
		this.nameOfRegionalCenter = nameOfRegionalCenter;
	}
	public Boolean getProgrammeThroughDistanceMode() {
		return programmeThroughDistanceMode;
	}
	public void setProgrammeThroughDistanceMode(Boolean programmeThroughDistanceMode) {
		this.programmeThroughDistanceMode = programmeThroughDistanceMode;
	}
		
}
