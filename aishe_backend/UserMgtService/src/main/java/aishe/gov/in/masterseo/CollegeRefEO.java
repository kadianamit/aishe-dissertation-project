package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "college")
public class CollegeRefEO implements Serializable {

    private static final long serialVersionUID = 6732141415304179701L;

    @EmbeddedId
    private StandAloneEmadedPK universityPk;
    private String name;
    @Column(name = "university_id")
    private String universityId;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "state_code")
    private String stateCode;
    @Column(name = "type_id")
    private Character typeId;
    @Column(name = "is_dcf_applicable")
    private Boolean isDcfApplicable;
    @Column(name = "user_by")
    private String userBy;
    @Column(name = "special_permission")
    private Boolean specialPermission;
    @Column(name = "permission_on_date")
    private LocalDateTime permissionOnDate;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StandAloneEmadedPK getUniversityPk() {
        return universityPk;
    }

    public void setUniversityPk(StandAloneEmadedPK universityPk) {
        this.universityPk = universityPk;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Character getTypeId() {
		return typeId;
	}

	public void setTypeId(Character typeId) {
		this.typeId = typeId;
	}

	public Boolean getIsDcfApplicable() {
		return isDcfApplicable;
	}

	public void setIsDcfApplicable(Boolean isDcfApplicable) {
		this.isDcfApplicable = isDcfApplicable;
	}

	public String getUserBy() {
		return userBy;
	}

	public void setUserBy(String userBy) {
		this.userBy = userBy;
	}

	public Boolean getSpecialPermission() {
		return specialPermission;
	}

	public void setSpecialPermission(Boolean specialPermission) {
		this.specialPermission = specialPermission;
	}

	public LocalDateTime getPermissionOnDate() {
		return permissionOnDate;
	}

	public void setPermissionOnDate(LocalDateTime permissionOnDate) {
		this.permissionOnDate = permissionOnDate;
	}
}