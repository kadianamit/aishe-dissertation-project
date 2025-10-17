package com.nic.aishe.master.dto;

public class AisheDetailsResponseDto {

	String aishecode;
	String institutionCategory;
	String institutionName;
	String state; // ref_state
	String district; // ref_district
	String affiliatingUniv;
	String eligibleSurveyYear;
	String instType;
	String mgmtBody; // ref_institution_management
	String sourceOfData;
	String website; // standalone_institution
	String addressLine1; // standalone_institution
	String addressLine2;// standalone_institution
	String email;// nodal_officer
	String designation;// nodal_officer
	String mobile;// nodal_officer
	String officerName; // nodal_officer
	String landline; // nodal_officer
	String headName;
	String headEmail;
	String headTelephone;
	String headMobile;
	String headDesignation;
	String status;

	public String getAishecode() {
		return aishecode;
	}

	public void setAishecode(String aishecode) {
		this.aishecode = aishecode;
	}

	public String getInstitutionCategory() {
		return institutionCategory;
	}

	public void setInstitutionCategory(String institutionCategory) {
		this.institutionCategory = institutionCategory;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAffiliatingUniv() {
		return affiliatingUniv;
	}

	public void setAffiliatingUniv(String affiliatingUniv) {
		this.affiliatingUniv = affiliatingUniv;
	}

	public String getEligibleSurveyYear() {
		return eligibleSurveyYear;
	}

	public void setEligibleSurveyYear(String eligibleSurveyYear) {
		this.eligibleSurveyYear = eligibleSurveyYear;
	}

	public String getInstType() {
		return instType;
	}

	public void setInstType(String instType) {
		this.instType = instType;
	}

	public String getMgmtBody() {
		return mgmtBody;
	}

	public void setMgmtBody(String mgmtBody) {
		this.mgmtBody = mgmtBody;
	}

	public String getSourceOfData() {
		return sourceOfData;
	}

	public void setSourceOfData(String sourceOfData) {
		this.sourceOfData = sourceOfData;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
//
//	@Override
//	public String toString() {
//		return "AisheDetailsResponseDto [aishecode=" + aishecode + ", institutionCategory=" + institutionCategory
//				+ ", institutionName=" + institutionName + ", state=" + state + ", district=" + district
//				+ ", affiliatingUnivId=" + affiliatingUniv + ", eligibleSurveyYear=" + eligibleSurveyYear
//				+ ", instType=" + instType + ", mgmtBody=" + mgmtBody + ", sourceOfData=" + sourceOfData + ", website="
//				+ website + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", email=" + email
//				+ ", designation=" + designation + ", mobile=" + mobile + ", officerName=" + officerName + ", landline="
//				+ landline + ", status=" + status + "]";
//	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getHeadEmail() {
		return headEmail;
	}

	public void setHeadEmail(String headEmail) {
		this.headEmail = headEmail;
	}

	public String getHeadTelephone() {
		return headTelephone;
	}

	public void setHeadTelephone(String headTelephone) {
		this.headTelephone = headTelephone;
	}

	public String getHeadMobile() {
		return headMobile;
	}

	public void setHeadMobile(String headMobile) {
		this.headMobile = headMobile;
	}

	public String getHeadDesignation() {
		return headDesignation;
	}

	public void setHeadDesignation(String headDesignation) {
		this.headDesignation = headDesignation;
	}

	@Override
	public String toString() {
		return "AisheDetailsResponseDto [aishecode=" + aishecode + ", institutionCategory=" + institutionCategory
				+ ", institutionName=" + institutionName + ", state=" + state + ", district=" + district
				+ ", affiliatingUniv=" + affiliatingUniv + ", eligibleSurveyYear=" + eligibleSurveyYear + ", instType="
				+ instType + ", mgmtBody=" + mgmtBody + ", sourceOfData=" + sourceOfData + ", website=" + website
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", email=" + email
				+ ", designation=" + designation + ", mobile=" + mobile + ", officerName=" + officerName + ", landline="
				+ landline + ", headName=" + headName + ", headEmail=" + headEmail + ", headTelephone=" + headTelephone
				+ ", headMobile=" + headMobile + ", headDesignation=" + headDesignation + ", status=" + status + "]";
	}

}
