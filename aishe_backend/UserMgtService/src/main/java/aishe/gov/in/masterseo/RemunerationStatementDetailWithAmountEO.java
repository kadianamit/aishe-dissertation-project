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
import java.sql.Timestamp;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "REM_TRANSACTION")
public class RemunerationStatementDetailWithAmountEO {
    @Column(name = "STATE_NAME")
    private String stateName;
    @Column(name = " INSTITUION_TYPE")
    private String institutionType;
    @Column(name = " USER_ID")
    private String userId;
    @Column(name = "INSTITUTION_ID")
    private String  aisheCode;
    @Column(name = "INSTITUTION_NAME")
    private String institutionName;
    @JsonIgnore
    @Column(name = "UPLOAD_DATE")
    private Timestamp fromUploadTimestamp;
    @Column(name = " SURVEY_YEAR")
    private String surveyYear;
    @Id
    @Column(name = "FORM_UPLOAD_ID")
    private String formUploadId;
    @JsonIgnore
    @Column(name = "TRANSACTION_TIMESTAMP")
    private Timestamp transactionTimestamp;
    @Column(name = "TRANSACTION_STATUS_ID")
    private String transactionStatusId;
    @Column(name = "TRANSACTION_STATUS_NAME")
    private String TransactionStatusName;
    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "TOTAL_NUMBER_OF_PROGRAMMES")
    private String totalNumberOfProgrammes;
    @Column(name = " ACCOUNT_HOLDER_NAME")
    private String accountHolderName;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Column(name = "IFSC_CODE")
    private String ifscCode;
    @Column(name = "PAN")
    private String pan;
    @Column(name = "EMAIL_ID")
    private String emailId;
    @Transient
    private String formUploadDateTime;
    @Transient
    private String transactionDateTime;

    public String getFormUploadDateTime() {
        if (null != fromUploadTimestamp)
            return DateUtils.convertDBDateTimeToString(fromUploadTimestamp.toLocalDateTime());
        return null;
    }

    public String getTransactionDateTime() {
        if (null != transactionTimestamp)
            return DateUtils.convertDBDateTimeToString(transactionTimestamp.toLocalDateTime());
        return null;
    }


}
