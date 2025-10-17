package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "college_institution_non_teaching_staff_count")
public class CollegeInstitutionNonTeachingStaffCount {
    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "non_teaching_staff_count_id")
    private Integer nonTeachingStaffId;

    @Column(name = "college_institution_id")
    private Integer collegeId;

    @Column(name = "survey_Year")
    private Integer surveyYear;

    @Column(name = "is_updated")
    private Boolean isUpdate;

    public Integer getNonTeachingStaffId() {
        return nonTeachingStaffId;
    }

    public void setNonTeachingStaffId(Integer nonTeachingStaffId) {
        this.nonTeachingStaffId = nonTeachingStaffId;
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

    public static CollegeInstitutionNonTeachingStaffCount bind(CollegeInstitutionNonTeachingStaffCount staffCount,Integer id,Integer aisheCode,Integer surveyYear){
        staffCount.setNonTeachingStaffId(id);
        staffCount.setSurveyYear(surveyYear);
        staffCount.setCollegeId(aisheCode);
        staffCount.setUpdate(false);
        return staffCount;
    }

    @Override
    public String toString() {
        return "CollegeInstitutionNonTeachingStaffCount{" + "nonTeachingStaffId=" + nonTeachingStaffId +
                ", collegeId=" + collegeId + ", surveyYear=" + surveyYear + ", isUpdate=" + isUpdate + '}';
    }
}
