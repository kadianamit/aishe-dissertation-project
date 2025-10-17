package com.nic.aishe.master.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "college_institution_accreditation")
public class CollegeInstitutionAccreditation {

    @Id
   // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "accreditation_id")
    private Integer accreditationId;


    @Column(name = "college_institution_id")
    private Integer collegeId;

    @Column(name = "survey_Year")
    private Integer surveyYear;

    @Column(name = "is_updated")
    private Boolean isUpdate;

    public static CollegeInstitutionAccreditation bind(CollegeInstitutionAccreditation accreditation, Integer id, Integer collegeId, Integer surveyYear) {
        accreditation.accreditationId = id;
        accreditation.collegeId = collegeId;
        accreditation.surveyYear = surveyYear;
        accreditation.isUpdate = false;
        return accreditation;
    }

    public Integer getAccreditationId() {
        return accreditationId;
    }

    public void setAccreditationId(Integer accreditationId) {
        this.accreditationId = accreditationId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
    }

    public Boolean getUpdate() {
        return isUpdate;
    }

    public void setUpdate(Boolean update) {
        isUpdate = update;
    }

    @Override
    public String toString() {
        return "CollegeInstitutionAccreditation{" + "accreditationId=" + accreditationId + ", collegeId=" + collegeId + ", surveyYear=" + surveyYear + ", isUpdate=" + isUpdate + '}';
    }
}
