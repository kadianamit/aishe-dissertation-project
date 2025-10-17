package aishe.gov.in.mastersvo;

import java.time.LocalDate;

public class RequestForAddInstitute {
	//college institution request detail
	private String instituteTypeCorS;
	
	private Integer  requestId;
	private Integer  surveyYear;
	private String  instituteName ;
	private String stateCode;
	private String districtCode;
	private String  collegeTypeId;
	private String  universityId;
	private String  locationId;
	private String managementId;
	private String admissionYear;
//	private Integer  createdCollegeInstitutionId;
//	private Integer  createdSurveyYear;
	private String  isAffiliatedToOtherUniversity;
	private String otherAffiliatedUniversityName;
	private String admissionProcessCompleted;
	//request approval details
//	private Integer  approverRoleId;
//	private String  approverId;
//	private Integer statusIdRequestApproval;
//	private String  remarks;
//	private LocalDate  approverDate;
	private String  ipAddress;
    //request details
	private Integer type;
	private byte[] documentPdf;
	//private Integer createdBy;
	//private Integer addressId;
	private LocalDate createdDate;
	//private String ipAddress;
	private Boolean isDeclarationAccepted;
	//private Integer institutionalHeadDetails;
    //user master
	private String userId;
	private Integer roleId;
	private String firstName;
	private String middleName;
	private String lastName;
//	private String fatherFirstName;
//	private String fatherMiddleName;
//	private String fatherLastName;
//	private String addressLine1;
//	private String addressLine2;
//	private String city;
//	private String phoneLandline;
//	private String phoneMobile;
//	private String emailId;
	private String userPasswordInHash;
//	private Integer isApproved;
//	private LocalDateTime approvedDatetime;
//	private LocalDateTime modifiedDatetime;
//	private String approvedBy;
//	private String modifiedBy;
	private String stateLevelBody;
	private String stateLevelBodyInstitute;
	private String bodyType;
//	private String addressStateCode;
//	private String addressDistrictCode;
	//private String imageName;
	//private byte[] approvLetter;
	//private String myAnswer;
	//private String securityQuestion;
	private String stdCode;
//	private LocalDateTime registrationDatetime;
	//private String ipAddress;
	//private Integer privilegeId;
	//private String approvalMessage;
	private String altEmailId;
//	private Integer statusId;
	private Integer deoUserType;
	private String aisheCode;
//	private Integer genderId;
	private String bcryptPassword;
    //standalone_institution_request_details
	private Integer  stateBodyId;
//	private String  locationId;
	private String  isAffiliatedToUniversity;
	private Integer  createdStandaloneInstitutionId;
	private Integer ministryId;
	
	//nodal officer
//	private String nodalOfficerdesignation;
//	private String nodalEmail;
//	private String nodalMobile;
//	private String nodalName;
//	private String nodalTelephone;
	
	private String directorName;
	private String directorTelephone;
	private String directorEmail;
	private String directorDesignation;
    private String directorMobile;
    private Integer directorGender;
    private Integer directorReason;
    //person
    private String personName;
	private String personDesignation;
	private String personEmail;
	private String personMobile;
	private String personLandline;
	private Integer personGender;
	private Integer personReason;
	//address
	private String personLine1;
	private String personLine2;
	private String personCity;
	private String personStateCode;
	private String personDistrictCode;
	private String personPincode;
	public String getInstituteTypeCorS() {
		return instituteTypeCorS;
	}
	public void setInstituteTypeCorS(String instituteTypeCorS) {
		this.instituteTypeCorS = instituteTypeCorS;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getCollegeTypeId() {
		return collegeTypeId;
	}
	public void setCollegeTypeId(String collegeTypeId) {
		this.collegeTypeId = collegeTypeId;
	}
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
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
	public String getAdmissionYear() {
		return admissionYear;
	}
	public void setAdmissionYear(String admissionYear) {
		this.admissionYear = admissionYear;
	}
	public String getIsAffiliatedToOtherUniversity() {
		return isAffiliatedToOtherUniversity;
	}
	public void setIsAffiliatedToOtherUniversity(String isAffiliatedToOtherUniversity) {
		this.isAffiliatedToOtherUniversity = isAffiliatedToOtherUniversity;
	}
	public String getOtherAffiliatedUniversityName() {
		return otherAffiliatedUniversityName;
	}
	public void setOtherAffiliatedUniversityName(String otherAffiliatedUniversityName) {
		this.otherAffiliatedUniversityName = otherAffiliatedUniversityName;
	}
	public String getAdmissionProcessCompleted() {
		return admissionProcessCompleted;
	}
	public void setAdmissionProcessCompleted(String admissionProcessCompleted) {
		this.admissionProcessCompleted = admissionProcessCompleted;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public byte[] getDocumentPdf() {
		return documentPdf;
	}
	public void setDocumentPdf(byte[] documentPdf) {
		this.documentPdf = documentPdf;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public Boolean getIsDeclarationAccepted() {
		return isDeclarationAccepted;
	}
	public void setIsDeclarationAccepted(Boolean isDeclarationAccepted) {
		this.isDeclarationAccepted = isDeclarationAccepted;
	}
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
	public String getUserPasswordInHash() {
		return userPasswordInHash;
	}
	public void setUserPasswordInHash(String userPasswordInHash) {
		this.userPasswordInHash = userPasswordInHash;
	}
	public String getStateLevelBody() {
		return stateLevelBody;
	}
	public void setStateLevelBody(String stateLevelBody) {
		this.stateLevelBody = stateLevelBody;
	}
	public String getStateLevelBodyInstitute() {
		return stateLevelBodyInstitute;
	}
	public void setStateLevelBodyInstitute(String stateLevelBodyInstitute) {
		this.stateLevelBodyInstitute = stateLevelBodyInstitute;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	public String getStdCode() {
		return stdCode;
	}
	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}
	public String getAltEmailId() {
		return altEmailId;
	}
	public void setAltEmailId(String altEmailId) {
		this.altEmailId = altEmailId;
	}
	public Integer getDeoUserType() {
		return deoUserType;
	}
	public void setDeoUserType(Integer deoUserType) {
		this.deoUserType = deoUserType;
	}
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
	public String getBcryptPassword() {
		return bcryptPassword;
	}
	public void setBcryptPassword(String bcryptPassword) {
		this.bcryptPassword = bcryptPassword;
	}
	public Integer getStateBodyId() {
		return stateBodyId;
	}
	public void setStateBodyId(Integer stateBodyId) {
		this.stateBodyId = stateBodyId;
	}
	public String getIsAffiliatedToUniversity() {
		return isAffiliatedToUniversity;
	}
	public void setIsAffiliatedToUniversity(String isAffiliatedToUniversity) {
		this.isAffiliatedToUniversity = isAffiliatedToUniversity;
	}
	public Integer getCreatedStandaloneInstitutionId() {
		return createdStandaloneInstitutionId;
	}
	public void setCreatedStandaloneInstitutionId(Integer createdStandaloneInstitutionId) {
		this.createdStandaloneInstitutionId = createdStandaloneInstitutionId;
	}
	public Integer getMinistryId() {
		return ministryId;
	}
	public void setMinistryId(Integer ministryId) {
		this.ministryId = ministryId;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getDirectorTelephone() {
		return directorTelephone;
	}
	public void setDirectorTelephone(String directorTelephone) {
		this.directorTelephone = directorTelephone;
	}
	public String getDirectorEmail() {
		return directorEmail;
	}
	public void setDirectorEmail(String directorEmail) {
		this.directorEmail = directorEmail;
	}
	public String getDirectorDesignation() {
		return directorDesignation;
	}
	public void setDirectorDesignation(String directorDesignation) {
		this.directorDesignation = directorDesignation;
	}
	public String getDirectorMobile() {
		return directorMobile;
	}
	public void setDirectorMobile(String directorMobile) {
		this.directorMobile = directorMobile;
	}
	public Integer getDirectorGender() {
		return directorGender;
	}
	public void setDirectorGender(Integer directorGender) {
		this.directorGender = directorGender;
	}
	public Integer getDirectorReason() {
		return directorReason;
	}
	public void setDirectorReason(Integer directorReason) {
		this.directorReason = directorReason;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonDesignation() {
		return personDesignation;
	}
	public void setPersonDesignation(String personDesignation) {
		this.personDesignation = personDesignation;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getPersonMobile() {
		return personMobile;
	}
	public void setPersonMobile(String personMobile) {
		this.personMobile = personMobile;
	}
	public String getPersonLandline() {
		return personLandline;
	}
	public void setPersonLandline(String personLandline) {
		this.personLandline = personLandline;
	}
	public Integer getPersonGender() {
		return personGender;
	}
	public void setPersonGender(Integer personGender) {
		this.personGender = personGender;
	}
	public Integer getPersonReason() {
		return personReason;
	}
	public void setPersonReason(Integer personReason) {
		this.personReason = personReason;
	}
	public String getPersonLine1() {
		return personLine1;
	}
	public void setPersonLine1(String personLine1) {
		this.personLine1 = personLine1;
	}
	public String getPersonLine2() {
		return personLine2;
	}
	public void setPersonLine2(String personLine2) {
		this.personLine2 = personLine2;
	}
	public String getPersonCity() {
		return personCity;
	}
	public void setPersonCity(String personCity) {
		this.personCity = personCity;
	}
	public String getPersonStateCode() {
		return personStateCode;
	}
	public void setPersonStateCode(String personStateCode) {
		this.personStateCode = personStateCode;
	}
	public String getPersonDistrictCode() {
		return personDistrictCode;
	}
	public void setPersonDistrictCode(String personDistrictCode) {
		this.personDistrictCode = personDistrictCode;
	}
	public String getPersonPincode() {
		return personPincode;
	}
	public void setPersonPincode(String personPincode) {
		this.personPincode = personPincode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}	
}