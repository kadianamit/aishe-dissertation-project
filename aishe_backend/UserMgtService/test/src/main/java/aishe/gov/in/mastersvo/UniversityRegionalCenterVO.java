package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UniversityRegionalCenterVO {
	
	
	@JsonProperty
	private String name;
	@JsonProperty
	private Integer noOfStudyCenter;
	@JsonProperty
	private String stateName;
	@JsonProperty
	private String districtName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNoOfStudyCenter() {
		return noOfStudyCenter;
	}
	public void setNoOfStudyCenter(Integer noOfStudyCenter) {
		this.noOfStudyCenter = noOfStudyCenter;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	

}
