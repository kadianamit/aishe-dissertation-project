package aishe.gov.in.mastersvo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.ToString;


@ToString
public class UserMasterDTO {
    private static final long serialVersionUID = 1L;

    private String userId;


    private Integer roleId;


    private String aisheCode;


    private String Name;


    private String addressLine1;

    private String addressLine2;

    private String city;
   // @JsonIgnore
    private String phoneLandline;

    private String stdCode;

    private String stateCode;


    private String districtId;

    private Integer isApproved;

    private String stateId;


   // @JsonIgnore
    private String mobile;

   // @JsonIgnore
    private String email;
    @JsonIgnore
    private String bcryptPassword;

    private String instituteName;

    private String stateName;

    private String districtName;

    private Integer LSY;

    private Integer minlsy;

    private String stateLevelBody;

    private Integer statusId;

    private Boolean isPasswordChange;

    private LocalDateTime passwordChangeDate;

    private Integer stateLgdCode;

    private Integer disLgdCode;

    private Double lattitude;

    private Double longitude;

    private boolean isFinalSubmit;

    private String roleName;


    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAisheCode() {
        return aisheCode;
    }

    public void setAisheCode(String aisheCode) {
        this.aisheCode = aisheCode;
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

    public String getPhoneLandline() {
        return phoneLandline;
    }

    public void setPhoneLandline(String phoneLandline) {
        this.phoneLandline = phoneLandline;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    public String getStdCode() {
        return stdCode;
    }

    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
    }

    public String getBcryptPassword() {
        return bcryptPassword;
    }

    public void setBcryptPassword(String bcryptPassword) {
        this.bcryptPassword = bcryptPassword;
    }

    public Integer getLSY() {
        return LSY;
    }

    public void setLSY(Integer lSY) {
        LSY = lSY;
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

    public Integer getMinlsy() {
        return minlsy;
    }

    public void setMinlsy(Integer minlsy) {
        this.minlsy = minlsy;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getStateLgdCode() {
        return stateLgdCode;
    }

    public void setStateLgdCode(Integer stateLgdCode) {
        this.stateLgdCode = stateLgdCode;
    }

    public Integer getDisLgdCode() {
        return disLgdCode;
    }

    public void setDisLgdCode(Integer disLgdCode) {
        this.disLgdCode = disLgdCode;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public boolean isFinalSubmit() {
        return isFinalSubmit;
    }

    public void setFinalSubmit(boolean isFinalSubmit) {
        this.isFinalSubmit = isFinalSubmit;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getIsPasswordChange() {
        return isPasswordChange;
    }

    public void setIsPasswordChange(Boolean isPasswordChange) {
        this.isPasswordChange = isPasswordChange;
    }

    public LocalDateTime getPasswordChangeDate() {
        return passwordChangeDate;
    }

    public void setPasswordChangeDate(LocalDateTime passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}