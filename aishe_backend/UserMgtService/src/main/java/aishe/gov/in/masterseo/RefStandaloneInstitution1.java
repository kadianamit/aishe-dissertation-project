package aishe.gov.in.masterseo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ref_standalone_institution")
public class RefStandaloneInstitution1 {
	@EmbeddedId
	private StandAloneEmadedPK universityPk;
    @Column(name = "name")
    private String name;
    @Column(name = "statebodyid")
    private Integer stateBodyId;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "statecode")
    private String stateCode;

    @Column(name = "user_by")
    private String userBy;
    @Column(name = "special_permission")
    private Boolean specialPermission;

    @Column(name = "permission_on_date")
    private Date permissionOnDate;

    @Column(name = "management_id")
    private String managementId ;

    @Column(name = "location")
    private String location ;

    public StandAloneEmadedPK getUniversityPk() {
		return universityPk;
	}

	public void setUniversityPk(StandAloneEmadedPK universityPk) {
		this.universityPk = universityPk;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStateBodyId() {
        return stateBodyId;
    }

    public void setStateBodyId(Integer stateBodyId) {
        this.stateBodyId = stateBodyId;
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

    public Date getPermissionOnDate() {
        return permissionOnDate;
    }

    public void setPermissionOnDate(Date permissionOnDate) {
        this.permissionOnDate = permissionOnDate;
    }

    public String getManagementId() {
        return managementId;
    }

    public void setManagementId(String managementId) {
        this.managementId = managementId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
