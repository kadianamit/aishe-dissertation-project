package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "college_institution_faculty")
public class CollegeInstitutionFacultyInfo implements Serializable {
    private static final long serialVersionUID = 8690843058969832724L;
    @Id
    @Column(name = "faculty_id")
    private Integer facultyId;


    @Column(name = "college_institution_id")
    private Integer collegeId;

    @Column(name = "survey_Year")
    private Integer surveyYear;

    @Column(name = "is_updated")
    private Boolean isUpdate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "faculty", joinColumns = {
            @JoinColumn(name = "id", referencedColumnName = "faculty_Id")},
            inverseJoinColumns =@JoinColumn(name = "faculty_Id", referencedColumnName = "id"))
    private FacultyInfo faculty;

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
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


    public FacultyInfo getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyInfo faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "CollegeInstitutionFaculty{" + "facultyId=" + facultyId + ", collegeId=" + collegeId + ", surveyYear=" + surveyYear + ", isUpdate=" + isUpdate + '}';
    }
}
