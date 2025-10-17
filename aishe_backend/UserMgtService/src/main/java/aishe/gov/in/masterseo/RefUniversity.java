package aishe.gov.in.masterseo;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ref_university")
public class RefUniversity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;

    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "statecode")
    private String stateCode;
    @Column(name = "type_id")
    private String typeId;
    @Where(clause = "surveyYear=(select max(survey_year) from ref_university)")
    @Column(name = "survey_year")
    private Integer surveyYear;
    @Column(name = "is_dcf_applicable")
    private Boolean isDcfApplicable;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
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
