package aishe.gov.in.mastersvo;

import javax.persistence.Column;

public class UserDetailsVo {

	private String userId;

	private Integer roleId;

	private String firstName;

	private String stateCode;

	private String mobile;

	private String email;

	private String aisheCode;

	//private String bcryptPassword;
	///////////new field
	private String districtCode;

	private Integer LSY;
	
	private Integer minlsy;

	private String instituteName;

	private String stateName;

	private String districtName;

	private String addressLine1;

	private String addressLine2;

	private String cityName;

	private String middleName;

	private String lastName;
	
	private String message;
	
	private String roleName;
	
	private String universityId;

	private Integer isApproved;
	
	private String universityName;
	
	private String landline;
	
	private String stdCode;
	
	private Integer statusId;
	
	private String bodyTypeId;
	//////////////////////created
	private Integer createdSurvey;

	private String collegeTypeId;
	
	private String locationId;
	
	private String managementId;
	
	private String pincode;
	
	private String name;

	private String designation;
	
	private String collegeOrStandaloneTypeName;

	private String managementName;
	
	
//	private String instituteHeadName;
//	private String instituteHeadDesignation;
//	private String instituteHeadEmail;
//	private String instituteHeadMobile;
//	private String instituteHeadTelephone;
//	private double latitude;
//	private double longitude;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
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

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getLSY() {
		return LSY;
	}

	public void setLSY(Integer lSY) {
		LSY = lSY;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getMinlsy() {
		return minlsy;
	}

	public void setMinlsy(Integer minlsy) {
		this.minlsy = minlsy;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public Integer getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getBodyTypeId() {
		return bodyTypeId;
	}

	public void setBodyTypeId(String bodyTypeId) {
		this.bodyTypeId = bodyTypeId;
	}

	public Integer getCreatedSurvey() {
		return createdSurvey;
	}

	public void setCreatedSurvey(Integer createdSurvey) {
		this.createdSurvey = createdSurvey;
	}

	public String getCollegeTypeId() {
		return collegeTypeId;
	}

	public void setCollegeTypeId(String collegeTypeId) {
		this.collegeTypeId = collegeTypeId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getManagementId() {
		return managementId;
	}

	public void setManagementId(String managementId) {
		this.managementId = managementId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCollegeOrStandaloneTypeName() {
		return collegeOrStandaloneTypeName;
	}

	public void setCollegeOrStandaloneTypeName(String collegeOrStandaloneTypeName) {
		this.collegeOrStandaloneTypeName = collegeOrStandaloneTypeName;
	}

	public String getManagementName() {
		return managementName;
	}

	public void setManagementName(String managementName) {
		this.managementName = managementName;
	}
}