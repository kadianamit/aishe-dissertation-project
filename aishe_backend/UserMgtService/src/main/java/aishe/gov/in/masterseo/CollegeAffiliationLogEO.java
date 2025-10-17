package aishe.gov.in.masterseo;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "college_affiliation_log")
public class CollegeAffiliationLogEO implements Serializable {

    private static final long serialVersionUID = 6732141415304179701L;
    @Id
    private Integer id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "deaffiliating_university_id")
    private String deaffiliatingUniversityId;
    @Column(name = "affiliating_university_id")
    private String affiliatingUniversityId;
    @Column(name = "deaffiliation_datetime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Timestamp deaffiliationDatetime;
    @Column(name = "affiliation_datetime")
    private Timestamp affiliationDatetime;
    @Column(name = "survey_year")
    private Integer surveyYear;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "deaffiliated_by")
    private String deaffiliatedBy;
    @Column(name = "affiliated_by")
    private String affiliatedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeaffiliatingUniversityId() {
        return deaffiliatingUniversityId;
    }

    public void setDeaffiliatingUniversityId(String deaffiliatingUniversityId) {
        this.deaffiliatingUniversityId = deaffiliatingUniversityId;
    }

    public String getAffiliatingUniversityId() {
        return affiliatingUniversityId;
    }

    public void setAffiliatingUniversityId(String affiliatingUniversityId) {
        this.affiliatingUniversityId = affiliatingUniversityId;
    }

    public Timestamp getDeaffiliationDatetime() {
        return deaffiliationDatetime;
    }

    public void setDeaffiliationDatetime(Timestamp deaffiliationDatetime) {
        this.deaffiliationDatetime = deaffiliationDatetime;
    }

    public Timestamp getAffiliationDatetime() {
        return affiliationDatetime;
    }

    public void setAffiliationDatetime(Timestamp affiliationDatetime) {
        this.affiliationDatetime = affiliationDatetime;
    }

    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
    }

    public String getDeaffiliatedBy() {
        return deaffiliatedBy;
    }

    public void setDeaffiliatedBy(String deaffiliatedBy) {
        this.deaffiliatedBy = deaffiliatedBy;
    }

    public String getAffiliatedBy() {
        return affiliatedBy;
    }

    public void setAffiliatedBy(String affiliatedBy) {
        this.affiliatedBy = affiliatedBy;
    }
}
