package aishe.gov.in.masterseo;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import aishe.gov.in.mastersvo.OtherAffiliatingUniversityDto;
import aishe.gov.in.mastersvo.StatuatoryBodyDto;
import lombok.ToString;


@Entity
@Table(name = "public.user_master")
@ToString
public class UserMasterNewEO {
    @Id
    @Column(name = "id")
    private Integer Id;
    
    @NotBlank
    @NotNull
    @Column(name = "user_id")
    private String userId;

	@Column(name = "role_id")
	private Integer roleId;
    @Column(name = "aishe_code")
    private String aisheCode;
   // @NotBlank
    //@NotNull
   /* @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;*/
    //@NotBlank
    //@NotNull

	@Column(name = "name")
	private String Name;
   /* @Column(name = "last_name")
    private String lastName;*/
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

    @Column(name = "address_state_code")
    private String stateId;

    // @Range(min = 10, max = 10, message = "mobile_no should be exact 10 characters.")
    @Column(name = "phone_mobile")
    private String mobile;
    //@Email
    @Column(name = "email_id")
    private String email;
    //@Column(name = "status_id")
    //private Integer statusId;
    @Transient
    private Boolean emailVerified;
    @Transient
    private Boolean mobileVerified;
    @Column(name = "body_type")
	private String bodyType;
    @Column(name = "privilege_id")
	private Integer privilegeId;
    
    @Column(name = "is_moe_display_user")
	private Boolean isMoeDisplayUser;
    
    @Column(name = "title_id")
	private Integer titleId;
    
//    @Column(name = "latitude")
//    private Double latitude;
//    @Column(name = "longitude")
//    private Double longitude;
//    
//    @Column(name = "institute_head_name")
//    private String instituteHeadName;
//    @Column(name = "institute_head_designation")
//    private String instituteHeadDesignation;
//    @Column(name = "institute_head_email")
//    private String instituteHeadEmail;
//    @Column(name = "institute_head_mobile")
//    private String instituteHeadMobile;
//    @Column(name = "institute_head_telephone")
//    private String instituteHeadTelephone;
    
    @Transient
    private String managementId;

    @Transient
	private String ownerShipId;

    @Transient
	private String locationId;
    @Transient
    private String nodalOfficerDesignation;
    @Transient
	private String pinCode;
    @Transient
    private String nodalOfficerName;
    
	@Transient
	private Integer headOfficerGender;
	@Transient
	private Integer nodalOfficerGender;
	
	@Transient
    private Integer ulbId;
	@Transient
 	private Integer blockId;
	@Transient
	private String website;
	@Transient
	private String universityId;
	@Transient
	private Double constructedArea;
	@Transient
	private Double area;
	@Transient
	private Double latitude;
	@Transient
	private Double longitude;
	
    @Transient
	private String instituteHeadName;
	@Transient
	private String instituteHeadDesignation;
	@Transient
	private String instituteHeadEmail;
	@Transient
	private String instituteHeadMobile;
	@Transient
    private String instituteHeadTelephone;
	@Transient
	private Boolean isGeospatialDataVerified;
	@Transient
	private Integer subDistrictId;
	@Transient
	private Boolean islgdDistrictVerified;
	@Transient
	private Boolean isLgdSubDistrictVerified;
	@Transient
	private Boolean isLgdStateVerified;
	@Transient
	private Boolean isBisagVerified;
	
	@Transient
	private Integer nodalOfficerTitleId;
	@Transient
	private Integer headOfficerTitleId;
	
	@Transient
	private Boolean isOtherAffiliatingUniversityStatuatoryBody;
	
	@Transient
	private List<OtherAffiliatingUniversityDto> otherAffiliatingUniversity;
	
	@Transient
	private List<StatuatoryBodyDto> statuatoryBody;
	
	@Transient
	private Integer ministryId;
	
	@Transient
	private Map<String,Map<String,String>> isInstituteDetailSaved;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getStdCode() {
        return stdCode;
    }

    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
    }

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public Boolean getMobileVerified() {
		return mobileVerified;
	}

	public void setMobileVerified(Boolean mobileVerified) {
		this.mobileVerified = mobileVerified;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getNodalOfficerDesignation() {
		return nodalOfficerDesignation;
	}

	public void setNodalOfficerDesignation(String nodalOfficerDesignation) {
		this.nodalOfficerDesignation = nodalOfficerDesignation;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getNodalOfficerName() {
		return nodalOfficerName;
	}

	public void setNodalOfficerName(String nodalOfficerName) {
		this.nodalOfficerName = nodalOfficerName;
	}

	public Integer getHeadOfficerGender() {
		return headOfficerGender;
	}

	public void setHeadOfficerGender(Integer headOfficerGender) {
		this.headOfficerGender = headOfficerGender;
	}

	public Integer getNodalOfficerGender() {
		return nodalOfficerGender;
	}

	public void setNodalOfficerGender(Integer nodalOfficerGender) {
		this.nodalOfficerGender = nodalOfficerGender;
	}

	public Integer getUlbId() {
		return ulbId;
	}

	public void setUlbId(Integer ulbId) {
		this.ulbId = ulbId;
	}

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public Double getConstructedArea() {
		return constructedArea;
	}

	public void setConstructedArea(Double constructedArea) {
		this.constructedArea = constructedArea;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getManagementId() {
		return managementId;
	}

	public void setManagementId(String managementId) {
		this.managementId = managementId;
	}

	public String getOwnerShipId() {
		return ownerShipId;
	}

	public void setOwnerShipId(String ownerShipId) {
		this.ownerShipId = ownerShipId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getInstituteHeadName() {
		return instituteHeadName;
	}

	public void setInstituteHeadName(String instituteHeadName) {
		this.instituteHeadName = instituteHeadName;
	}

	public String getInstituteHeadDesignation() {
		return instituteHeadDesignation;
	}

	public void setInstituteHeadDesignation(String instituteHeadDesignation) {
		this.instituteHeadDesignation = instituteHeadDesignation;
	}

	public String getInstituteHeadEmail() {
		return instituteHeadEmail;
	}

	public void setInstituteHeadEmail(String instituteHeadEmail) {
		this.instituteHeadEmail = instituteHeadEmail;
	}

	public String getInstituteHeadMobile() {
		return instituteHeadMobile;
	}

	public void setInstituteHeadMobile(String instituteHeadMobile) {
		this.instituteHeadMobile = instituteHeadMobile;
	}

	public String getInstituteHeadTelephone() {
		return instituteHeadTelephone;
	}

	public void setInstituteHeadTelephone(String instituteHeadTelephone) {
		this.instituteHeadTelephone = instituteHeadTelephone;
	}

	public Boolean getIsMoeDisplayUser() {
		return isMoeDisplayUser;
	}

	public void setIsMoeDisplayUser(Boolean isMoeDisplayUser) {
		this.isMoeDisplayUser = isMoeDisplayUser;
	}

	public Boolean getIsGeospatialDataVerified() {
		return isGeospatialDataVerified;
	}

	public void setIsGeospatialDataVerified(Boolean isGeospatialDataVerified) {
		this.isGeospatialDataVerified = isGeospatialDataVerified;
	}

	public Integer getSubDistrictId() {
		return subDistrictId;
	}

	public void setSubDistrictId(Integer subDistrictId) {
		this.subDistrictId = subDistrictId;
	}

	public Boolean getIslgdDistrictVerified() {
		return islgdDistrictVerified;
	}

	public void setIslgdDistrictVerified(Boolean islgdDistrictVerified) {
		this.islgdDistrictVerified = islgdDistrictVerified;
	}

	public Boolean getIsLgdSubDistrictVerified() {
		return isLgdSubDistrictVerified;
	}

	public void setIsLgdSubDistrictVerified(Boolean isLgdSubDistrictVerified) {
		this.isLgdSubDistrictVerified = isLgdSubDistrictVerified;
	}

	public Boolean getIsLgdStateVerified() {
		return isLgdStateVerified;
	}

	public void setIsLgdStateVerified(Boolean isLgdStateVerified) {
		this.isLgdStateVerified = isLgdStateVerified;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Boolean getIsBisagVerified() {
		return isBisagVerified;
	}

	public void setIsBisagVerified(Boolean isBisagVerified) {
		this.isBisagVerified = isBisagVerified;
	}

	public Integer getNodalOfficerTitleId() {
		return nodalOfficerTitleId;
	}

	public void setNodalOfficerTitleId(Integer nodalOfficerTitleId) {
		this.nodalOfficerTitleId = nodalOfficerTitleId;
	}

	public Integer getHeadOfficerTitleId() {
		return headOfficerTitleId;
	}

	public void setHeadOfficerTitleId(Integer headOfficerTitleId) {
		this.headOfficerTitleId = headOfficerTitleId;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Boolean getIsOtherAffiliatingUniversityStatuatoryBody() {
		return isOtherAffiliatingUniversityStatuatoryBody;
	}

	public void setIsOtherAffiliatingUniversityStatuatoryBody(Boolean isOtherAffiliatingUniversityStatuatoryBody) {
		this.isOtherAffiliatingUniversityStatuatoryBody = isOtherAffiliatingUniversityStatuatoryBody;
	}

	public List<OtherAffiliatingUniversityDto> getOtherAffiliatingUniversity() {
		return otherAffiliatingUniversity;
	}

	public void setOtherAffiliatingUniversity(List<OtherAffiliatingUniversityDto> otherAffiliatingUniversity) {
		this.otherAffiliatingUniversity = otherAffiliatingUniversity;
	}

	public List<StatuatoryBodyDto> getStatuatoryBody() {
		return statuatoryBody;
	}

	public void setStatuatoryBody(List<StatuatoryBodyDto> statuatoryBody) {
		this.statuatoryBody = statuatoryBody;
	}

	public Integer getMinistryId() {
		return ministryId;
	}

	public void setMinistryId(Integer ministryId) {
		this.ministryId = ministryId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Map<String, Map<String, String>> getIsInstituteDetailSaved() {
		return isInstituteDetailSaved;
	}

	public void setIsInstituteDetailSaved(Map<String, Map<String, String>> isInstituteDetailSaved) {
		this.isInstituteDetailSaved = isInstituteDetailSaved;
	}
	
	
}