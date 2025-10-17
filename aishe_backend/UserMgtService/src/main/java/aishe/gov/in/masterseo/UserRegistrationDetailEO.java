package aishe.gov.in.masterseo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "public.user_master_request")
public class UserRegistrationDetailEO {

	@Id
	@Column(name = "user_id")
	private String userId;
	@Column(name = "role_id")
	private Integer roleId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "address_line1")
	private String addressLine1;
	@Column(name = "address_line2")
	private String addressLine2;
	@Column(name = "city")
	private String city;
	@Column(name = "state_code")
	private String stateCode;
	@Column(name = "phone_landline")
	private String phoneLandline;
	@Column(name = "phone_mobile")
	private String phoneMobile;
	@Column(name = "email_id")
	private String emailId;

	@Column(name = "is_approved")
	private Integer isApproved;
	
	@Column(name = "state_level_body")
	private String stateLevelBody;
	@Column(name = "state_level_body_institute")
	private String stateLevelBodyInstitute;

	@Column(name = "address_state_code")
	private String addressStateCode;
	@Column(name = "address_district_code")
	private String addressDistrictCode;
	
	@Column(name = "std_code")
	private String stdCode;
	@Column(name = "registration_datetime")///self
	private LocalDateTime registrationDatetime;
	@Column(name = "ip_address")
	private String ipAddress;
	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "alt_email_id")
	private String altEmailId;
	
	@Column(name = "aishe_code")
	private String aisheCode;
	@Column(name = "gender")
	private Integer genderId;
	@Column(name = "bcrypt_password")
	private String bcryptPassword;
	@Column(name = "user_password") // hash password
	private String userPasswordInHash;
	@Transient
	private String confirmBcryptPassword;
	@Column(name = "document_pdf")
	private byte[] document;
	@Column(name = "document_name")
	private String documentName;
	@Column(name = "body_type")
	private String bodyType;
	@Column(name = "privilege_id")
	private Integer privilegeId;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getPhoneLandline() {
		return phoneLandline;
	}
	public void setPhoneLandline(String phoneLandline) {
		this.phoneLandline = phoneLandline;
	}
	public String getPhoneMobile() {
		return phoneMobile;
	}
	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Integer getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
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
	public String getAddressStateCode() {
		return addressStateCode;
	}
	public void setAddressStateCode(String addressStateCode) {
		this.addressStateCode = addressStateCode;
	}
	public String getAddressDistrictCode() {
		return addressDistrictCode;
	}
	public void setAddressDistrictCode(String addressDistrictCode) {
		this.addressDistrictCode = addressDistrictCode;
	}
	public String getStdCode() {
		return stdCode;
	}
	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}
	public LocalDateTime getRegistrationDatetime() {
		return registrationDatetime;
	}
	public void setRegistrationDatetime(LocalDateTime registrationDatetime) {
		this.registrationDatetime = registrationDatetime;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getAltEmailId() {
		return altEmailId;
	}
	public void setAltEmailId(String altEmailId) {
		this.altEmailId = altEmailId;
	}
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}
	public String getBcryptPassword() {
		return bcryptPassword;
	}
	public void setBcryptPassword(String bcryptPassword) {
		this.bcryptPassword = bcryptPassword;
	}
	public String getConfirmBcryptPassword() {
		return confirmBcryptPassword;
	}
	public void setConfirmBcryptPassword(String confirmBcryptPassword) {
		this.confirmBcryptPassword = confirmBcryptPassword;
	}
	public String getUserPasswordInHash() {
		return userPasswordInHash;
	}
	public void setUserPasswordInHash(String userPasswordInHash) {
		this.userPasswordInHash = userPasswordInHash;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public byte[] getDocument() {
		return document;
	}
	public void setDocument(byte[] document) {
		this.document = document;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
}