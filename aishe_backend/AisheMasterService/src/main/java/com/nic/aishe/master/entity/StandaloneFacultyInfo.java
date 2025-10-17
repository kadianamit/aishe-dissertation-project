package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "standalone_institution_faculty")
public class StandaloneFacultyInfo implements Serializable {

    private static final long serialVersionUID = 8690843058969832724L;
    @Id
    @Column(name = "faculty_id")
    private Integer facultyId;

    @Column(name = "standalone_institution_id")
    private Integer standaloneId;

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

    public Integer getStandaloneId() {
        return standaloneId;
    }

    public void setStandaloneId(Integer standaloneId) {
        this.standaloneId = standaloneId;
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

    public static StandaloneFacultyInfo bindStandaloneFacultyInfo(StandaloneInstitutionFacultyInfo dto) {
        StandaloneFacultyInfo facultyInfo=new StandaloneFacultyInfo();
        facultyInfo.setSurveyYear(dto.getSurveyYear());
        facultyInfo.setFacultyId( dto.getFacultyId());
        facultyInfo.setUpdate(dto.getUpdate());
        facultyInfo.setStandaloneId(dto.getStandaloneId());
        return facultyInfo;
    }

    public static StandaloneFacultyInfo bind(StandaloneInstitutionFacultyInfo dto) {
        StandaloneFacultyInfo facultyInfo=new StandaloneFacultyInfo();
        facultyInfo.setSurveyYear(dto.getSurveyYear());
        facultyInfo.setFacultyId( dto.getFacultyId());
        facultyInfo.setUpdate(true);
        facultyInfo.setStandaloneId(dto.getStandaloneId());
        return facultyInfo;
    }

    @Override
    public String toString() {
        return "StandaloneFacultyInfo{" +
                "facultyId=" + facultyId +
                ", standaloneId=" + standaloneId +
                ", surveyYear=" + surveyYear +
                ", isUpdate=" + isUpdate +
                '}';
    }
}
