package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "FORM_UPLOAD")
public class FormDetailEO {
    @Id
    @Column(name = "USER_ID")
    private  String userId;
    @Column(name = "ROLE_ID")
    private  Integer roleId;
    @Column(name = "ROLE_NAME")
    private  String roleName;
    @Column(name = "NAME")
    private  String name;
    @Column(name = "PHONE_LANDLINE")
    private  String phoneLandline;
    @Column(name = "PHONE_MOBILE")
    private  String phoneMobile;
    @Column(name = "EMAIL_ID")
    private  String emailId;
    @Column(name = "ALT_EMAIL_ID")
    private  String altEmailId;
    @Column(name = "INSTITUTION_NAME")
    private  String institutionName;
    @Column(name = "AISHE_CODE")
    private  String aisheCode;
    @Column(name = "FORM_ID")
    private  String formId;
    @Column(name = "APPROVED_DATETIME")
    private Timestamp approvedDateTime;
    @Column(name = "survey_year")
    private  Integer surveyYear;
    @Column(name = "amount")
    private BigInteger amount;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    @Column(name = "account_Number")
    private String accountNumber;
    @Column(name = "ifsc_code")
    private String ifsc_code;
    @Column(name = "PAN")
    private String PAN;

}
