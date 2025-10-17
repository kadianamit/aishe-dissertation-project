package aishe.gov.in.masterseo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_action_log")
public class UserActionLogEONew implements Serializable {

    private static final long serialVersionUID = 6732141415304179701L;
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "institution_id")
    private String institutionId;
    @Column(name = "institution_type")
    private String institutionType;
    @Column(name = "action_id")
    private Integer actionId;
    @Column(name = "survey_year")
    private Integer surveyYear;
    @Column(name = "action_time")
    private Timestamp actionTime;
    @Column(name = "ip_address")
    private String ipAddress;
    private String remarks;

    public UserActionLogEONew() {
    }

    public UserActionLogEONew(String userId, String institutionId, String institutionType, Integer actionId, Integer surveyYear, Timestamp actionTime, String ipAddress, String remarks) {
        this.userId = userId;
        this.institutionId = institutionId;
        this.institutionType = institutionType;
        this.actionId = actionId;
        this.surveyYear = surveyYear;
        this.actionTime = actionTime;
        this.ipAddress = ipAddress;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
    }

    public Timestamp getActionTime() {
        return actionTime;
    }

    public void setActionTime(Timestamp actionTime) {
        this.actionTime = actionTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
