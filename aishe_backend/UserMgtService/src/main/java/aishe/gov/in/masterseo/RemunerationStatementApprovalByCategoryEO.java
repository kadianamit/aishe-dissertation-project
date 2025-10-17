package aishe.gov.in.masterseo;

import aishe.gov.in.utility.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigInteger;
import java.time.LocalDateTime;
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "SUBQUERY")
public class RemunerationStatementApprovalByCategoryEO {
    @Id
    @Column(name ="FORM_UPLOAD_ID" )
    private Integer formUploadId;
    @Column(name ="STATE_NAME" )
    private String stateName;
    @Column(name ="INSTITUION_TYPE" )
    private String institutionType;
    @Column(name ="FORM_ID" )
    private String fromId;
    @Column(name ="INSTITUTION_ID" )
    private String aisheCode;
    @Column(name ="INSTITUTION_NAME" )
    private String institutionName;
    @JsonIgnore
    @Column(name ="UPLOAD_DATE" )
    private LocalDateTime formUploadDate;
    @Column(name ="SURVEY_YEAR" )
    private Integer surveyYear;
    @Column(name ="NO_OF_PROGRAMME" )
    private Integer numberOfProgrammes;
    @Column(name ="UNIVERSITY_NAME" )
    private String universityName;
    @Column(name ="MOE_APPROVAL_STATUS_ID" )
    private Integer moeApprovalStatusId;
    @Column(name ="SNO_APPROVAL_STATUS_ID" )
    private Integer snoApprovalStatusId;
    @Column(name ="UNO_APPROVAL_STATUS_ID" )
    private Integer unoApprovalStatusId;
    @Column(name ="MOE_APPROVAL_STATUS_NAME" )
    private String moeApprovalStatusName;
    @Column(name ="SNO_APPROVAL_STATUS_NAME" )
    private String snoApprovalStatusName;
    @Column(name ="UNO_APPROVAL_STATUS_NAME" )
    private String unoApprovalStatusName;
    @Column(name ="MOE_REMARKS" )
    private String moeRemarks;
    @Column(name ="SNO_REMARKS" )
    private String snoRemarks;
    @Column(name ="UNO_REMARKS" )
    private String unoRemarks;
    @JsonIgnore
    @Column(name ="MOE_TIMESTAMP" )
    private LocalDateTime moeTimestamp;
    @JsonIgnore
    @Column(name ="SNO_TIMESTAMP" )
    private LocalDateTime snoTimestamp;
    @JsonIgnore
    @Column(name ="UNO_TIMESTAMP" )
    private LocalDateTime unoTimestamp;
    @Column(name ="MOE_APPROVER_ID" )
    private String moeApproverUserId;
    @Column(name ="SNO_APPROVER_ID" )
    private String noApproverUserId;
    @Column(name ="UNO_APPROVER_ID" )
    private String unoApproverUserId;
    @Column(name ="constituted_from_colleges" )
    private Boolean constitutedFromColleges;
    @Column(name ="EMAIL_ID" )
    private String emailId;
    @Transient
    private String formUploadDateTime;
    @Transient
    private String moeApprovalDateTime;
    @Transient
    private String snoApprovalDateTime;
    @Transient
    private String unoApprovalDateTime;
    @Transient
    private BigInteger amount;
    @Transient
    private Integer normTypeId;

    public String getFormUploadDateTime() {
        if (null != formUploadDate)
            return DateUtils.convertDBDateTimeToString(formUploadDate);
        return null;
    }

    public String getMoeApprovalDateTime() {
        if (null != moeTimestamp)
            return DateUtils.convertDBDateTimeToString(moeTimestamp);
        return null;
    }

    public String getSnoApprovalDateTime() {
        if (null != snoTimestamp)
            return DateUtils.convertDBDateTimeToString(snoTimestamp);
        return null;
    }

    public String getUnoApprovalDateTime() {
        if (null != unoTimestamp)
            return DateUtils.convertDBDateTimeToString(unoTimestamp);
        return null;
    }
}
