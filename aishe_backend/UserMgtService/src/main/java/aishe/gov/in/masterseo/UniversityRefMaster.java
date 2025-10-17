package aishe.gov.in.masterseo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ref_university")
public class UniversityRefMaster implements Serializable {

    private static final long serialVersionUID = 6732141415304179701L;
    @EmbeddedId
    private UniversityEmadedPK universityEmaded;
    private String name;
    @Column(name = "statecode")
    private String stateCode;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "is_dcf_applicable")
    private Boolean isDcfApplicable;
    @Column(name = "type_id")
    private String typeId;
    @Column(name = "user_by")
    private String userBy;
    @Column(name = "special_permission")
    private Boolean specialPermission;
    @Column(name = "permission_on_date")
    private LocalDateTime permissionOnDate;
    public UniversityEmadedPK getUniversityEmaded() {
		return universityEmaded;
	}

	public void setUniversityEmaded(UniversityEmadedPK universityEmaded) {
		this.universityEmaded = universityEmaded;
	}

	public Boolean getIsDcfApplicable() {
		return isDcfApplicable;
	}

	public void setIsDcfApplicable(Boolean isDcfApplicable) {
		this.isDcfApplicable = isDcfApplicable;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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

    public Boolean getDcfApplicable() {
        return isDcfApplicable;
    }

    public void setDcfApplicable(Boolean dcfApplicable) {
        isDcfApplicable = dcfApplicable;
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