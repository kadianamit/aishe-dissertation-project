package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.ToString;


@Entity
@Table(name = "public.user_master")
@ToString
public class UserMaster {
    private static final long serialVersionUID = 1L;
    @Id
    @NotBlank
    @NotNull
    @Column(name = "user_id")
    private String userId;

    @OneToOne
    @JoinColumn(name = "role_id")
    private UserRole roleId;

    @Column(name = "aishe_code")
    private String aisheCode;

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
    @Column(name = "phone_landline")
    private String phoneLandline;
    @Column(name = "std_code")
    private String stdCode;
    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "address_district_code")
    private String districtId;
    @Column(name = "is_approved")
    private Integer isApproved;
    @Column(name = "address_state_code")
    private String stateId;

    // @Range(min = 10, max = 10, message = "mobile_no should be exact 10 characters.")
    @Column(name = "phone_mobile")
    private String mobile;
    @Email
    @Column(name = "email_id")
    private String email;

    @Column(name = "bcrypt_password")
    private String bcryptPassword;
    @Transient
    private String instituteName;
    @Transient
    private String stateName;
    @Transient
    private String districtName;
    @Transient
    private Integer LSY;
    @Transient
    private Integer minlsy;
    @Column(name = "state_level_body")
    private String stateLevelBody;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "title_id")
  	private Integer titleId;
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

    public UserRole getRoleId() {
        return roleId;
    }

    public void setRoleId(UserRole roleId) {
        this.roleId = roleId;
    }

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}
    
}

