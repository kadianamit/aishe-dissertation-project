package aishe.gov.in.mastersvo;

import java.util.List;

public class RegionalCenterVO {
	
	private Boolean distanEducationMode;
	private Integer noOfRegionalCenters;
	private List<UniversityRegionalCenterVO> universityRegionalCenters;
	
	public Boolean getDistanEducationMode() {
		return distanEducationMode;
	}
	public void setDistanEducationMode(Boolean distanEducationMode) {
		this.distanEducationMode = distanEducationMode;
	}
	public Integer getNoOfRegionalCenters() {
		return noOfRegionalCenters;
	}
	public void setNoOfRegionalCenters(Integer noOfRegionalCenters) {
		this.noOfRegionalCenters = noOfRegionalCenters;
	}
	public List<UniversityRegionalCenterVO> getUniversityRegionalCenters() {
		return universityRegionalCenters;
	}
	public void setUniversityRegionalCenters(List<UniversityRegionalCenterVO> universityRegionalCenters) {
		this.universityRegionalCenters = universityRegionalCenters;
	}
	
	
	

}
