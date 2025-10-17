package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "university_faculty")

public class UniversityInfoFaculty implements Serializable {

    private static final long serialVersionUID = 8690843058969832724L;
    @Id
    @Column(name = "faculty_id")
    private Integer facultyId;

    @Column(name = "university_id")
    private String universityId;

    @Column(name = "survey_Year")
    private Integer surveyYear;

    @Column(name = "is_updated")
    private Boolean isUpdate;

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
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
    public static UniversityInfoFaculty bindUniversityFacultyInfo(UniversityFacultyInfo dto) {
        UniversityInfoFaculty facultyInfo=new UniversityInfoFaculty();
        facultyInfo.setSurveyYear(dto.getSurveyYear());
        facultyInfo.setFacultyId( dto.getFacultyId());
        facultyInfo.setUpdate(dto.getUpdate());
        facultyInfo.setUniversityId(dto.getUniversityId());
        return facultyInfo;
    }

    public static UniversityInfoFaculty bind(UniversityFacultyInfo dto) {
        UniversityInfoFaculty facultyInfo=new UniversityInfoFaculty();
        facultyInfo.setSurveyYear(dto.getSurveyYear());
        facultyInfo.setFacultyId(dto.getFacultyId());
        facultyInfo.setUpdate(true);
        facultyInfo.setUniversityId(dto.getUniversityId());
        return facultyInfo;
    }


    @Override
    public String toString() {
        return "UniversityInfoFaculty{" +
                "facultyId=" + facultyId +
                ", universityId='" + universityId + '\'' +
                ", surveyYear=" + surveyYear +
                ", isUpdate=" + isUpdate +
                '}';
    }
}
