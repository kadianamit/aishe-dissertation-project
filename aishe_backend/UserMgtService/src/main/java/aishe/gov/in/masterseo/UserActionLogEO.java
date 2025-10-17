package aishe.gov.in.masterseo;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import aishe.gov.in.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "public.user_action_log")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserActionLogEO {
    @Id
    //@GenericGenerator(name="useractnlog" , strategy="increment")
	//@GeneratedValue(generator="useractnlog")
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
    private Integer surveyYear = 0;
    @Column(name = "action_time")
    private Timestamp actionTime;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "remarks")
    private String remarks;

    public static UserActionLogEO bind(UserMasterNewEO masterEO, String ipAddress) {
        UserActionLogEO logEO = new UserActionLogEO();
        logEO.setUserId(masterEO.getUserId());

        if (null != masterEO.getAisheCode()) {
            if (!(masterEO.getAisheCode().isEmpty())) {
                logEO.setInstitutionId((masterEO.getAisheCode().trim().split("\\s*-\\s*"))[1]);
                logEO.setInstitutionType((masterEO.getAisheCode().trim().split("\\s*-\\s*"))[0]);
            }
        }
        logEO.setActionId(ActionType.EDIT_REGISTRATION.getActionType());
        logEO.setSurveyYear(0);
        logEO.setActionTime(new Timestamp(new Date().getTime()));
        logEO.setIpAddress(ipAddress);
        logEO.setRemarks("User-Data");
        return logEO;
    }

    public static UserActionLogEO bind(UserMasterDetailEO masterEO, Integer actionId, Integer surveyYear, String remarks, String ipAddress) {
        UserActionLogEO logEO = new UserActionLogEO();
        logEO.setUserId(masterEO.getUserId());

        if (null != masterEO.getAisheCode()) {
            if (!(masterEO.getAisheCode().isEmpty())) {
                logEO.setInstitutionId((masterEO.getAisheCode().trim().split("\\s*-\\s*"))[1]);
                logEO.setInstitutionType((masterEO.getAisheCode().trim().split("\\s*-\\s*"))[0]);
            }
        }
        logEO.setActionId(actionId);
        logEO.setSurveyYear(surveyYear);
        logEO.setActionTime(new Timestamp(new Date().getTime()));
        logEO.setIpAddress(ipAddress);
        logEO.setRemarks(remarks);
        return logEO;
    }

    public static UserActionLogEO bind(UserMasterDetailEO masterEO, Integer actionId, Integer surveyYear, String remarks, String ipAddress, String approvedBy) {
        UserActionLogEO logEO = new UserActionLogEO();
        logEO.setUserId(approvedBy);

        if (null != masterEO.getAisheCode()) {
            if (!(masterEO.getAisheCode().isEmpty())) {
                logEO.setInstitutionId((masterEO.getAisheCode().trim().split("\\s*-\\s*"))[1]);
                logEO.setInstitutionType((masterEO.getAisheCode().trim().split("\\s*-\\s*"))[0]);
            }
        }
        logEO.setActionId(actionId);
        logEO.setSurveyYear(surveyYear);
        logEO.setActionTime(new Timestamp(new Date().getTime()));
        logEO.setIpAddress(ipAddress);
        logEO.setRemarks(remarks);
        return logEO;
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
