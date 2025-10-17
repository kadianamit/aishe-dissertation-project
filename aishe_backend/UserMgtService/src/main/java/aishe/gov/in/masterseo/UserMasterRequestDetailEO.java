package aishe.gov.in.masterseo;

import aishe.gov.in.utility.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "public.user_master_request")
public class UserMasterRequestDetailEO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    @Column(name = "user_id", unique = true)
    private String userId;
    
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "name")
    private String Name;
    //@Column(name = "middle_name")
    //private String middleName;
    //@Column(name = "last_name")
    //private String lastName;
    @Column(name = "fathers_first_name")
    private String fatherFirstName;
    @Column(name = "fathers_middle_name")
    private String fatherMiddleName;
    @Column(name = "fathers_last_name")
    private String fatherLastName;
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
    @Column(name = "user_password") // hash password
    private String userPasswordInHash;
    @Column(name = "is_approved")
    private Integer isApproved;
    @Column(name = "approved_datetime")
    private LocalDateTime approvedDatetime;
    @Column(name = "modified_datetime")
    private LocalDateTime modifiedDatetime;
    @Column(name = "approved_by")
    private String approvedBy;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "state_level_body")
    private String stateLevelBody;
    @Column(name = "state_level_body_institute")
    private String stateLevelBodyInstitute;
    @Column(name = "body_type")
    private String bodyType;
    @Column(name = "address_state_code")
    private String addressStateCode;
    @Column(name = "address_district_code")
    private String addressDistrictCode;
    @Column(name = "image_name")
    private String imageName;
    @JsonIgnore
    @Column(name = "approv_letter")
    private byte[] approvLetter;
    @Column(name = "my_answer")
    private String myAnswer;
    @Column(name = "security_question")
    private String securityQuestion;
    @Column(name = "std_code")
    private String stdCode;
    @Column(name = "registration_datetime")
    private LocalDateTime registrationDatetime;
    @JsonIgnore
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "privilege_id")
    private Integer privilegeId;
    @Column(name = "approval_message")
    private String approvalMessage;
    @Column(name = "alt_email_id")
    private String altEmailId;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "deo_user_type")
    private Integer deoUserType;
    @Column(name = "aishe_code")
    private String aisheCode;
    @Column(name = "gender")
    private Integer genderId;

    @Column(name = "bcrypt_password")
    private String bcryptPassword;
    @Column(name = "request_id")
    private Integer requestId;

    @JsonIgnore
    @Column(name = "document_pdf")
    private byte[] document;
    @Column(name = "document_name")
    private String documentName;
    @Transient
    private String confirmBcryptPassword;
    @Transient
    private String registrationDate;

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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFatherFirstName() {
        return fatherFirstName;
    }

    public void setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
    }

    public String getFatherMiddleName() {
        return fatherMiddleName;
    }

    public void setFatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
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

    public String getUserPasswordInHash() {
        return userPasswordInHash;
    }

    public void setUserPasswordInHash(String userPasswordInHash) {
        this.userPasswordInHash = userPasswordInHash;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public LocalDateTime getApprovedDatetime() {
        return approvedDatetime;
    }

    public void setApprovedDatetime(LocalDateTime approvedDatetime) {
        this.approvedDatetime = approvedDatetime;
    }

    public LocalDateTime getModifiedDatetime() {
        return modifiedDatetime;
    }

    public void setModifiedDatetime(LocalDateTime modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getApprovLetter() {
        return approvLetter;
    }

    public void setApprovLetter(byte[] approvLetter) {
        this.approvLetter = approvLetter;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
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

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getApprovalMessage() {
        return approvalMessage;
    }

    public void setApprovalMessage(String approvalMessage) {
        this.approvalMessage = approvalMessage;
    }

    public String getAltEmailId() {
        return altEmailId;
    }

    public void setAltEmailId(String altEmailId) {
        this.altEmailId = altEmailId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getConfirmBcryptPassword() {
        return confirmBcryptPassword;
    }

    public void setConfirmBcryptPassword(String confirmBcryptPassword) {
        this.confirmBcryptPassword = confirmBcryptPassword;
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

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

	public String getRegistrationDate() {
		if(null !=registrationDatetime){
			return DateUtils.convertDBDateTimeToStringNew(registrationDatetime);
		}
		return registrationDate;
	}


}