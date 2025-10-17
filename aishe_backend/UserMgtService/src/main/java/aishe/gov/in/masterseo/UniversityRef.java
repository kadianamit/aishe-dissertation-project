package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ref_university")
public class UniversityRef implements Serializable {

    private static final long serialVersionUID = 6732141415304179701L;
    @Id
    private String id;
    private String name;
    @Column(name = "statecode")
    private String stateCode;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "is_dcf_applicable")
    private Boolean isDcfApplicable;
    @Column(name = "survey_year")
    private Integer surveyYear;
    @Column(name = "type_id")
    private Character typeId;

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

    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
    }

    public Character getTypeId() {
        return typeId;
    }

    public void setTypeId(Character typeId) {
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
}
