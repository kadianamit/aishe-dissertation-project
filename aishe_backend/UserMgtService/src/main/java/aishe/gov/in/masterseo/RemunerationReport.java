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
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Entity
@Table(name = "remuneration_report")
public class RemunerationReport {

    @Column(name = "institution_type")
    private String institutionType;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "survey_year")
    private String surveyYear;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "ifsc_code")
    private String ifscCode;
    @JsonIgnore
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "pan")
    private String pan;
    @Id
    @Column(name = "aishe_code")
    private String aisheCode;
    @Column(name = "bank_address")
    private String bankAddress;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "pin_code")
    private String pinCode;
    @Column(name = "state_code")
    private String stateCode;
    @Column(name = "state_name")
    private String stateName;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "district_name")
    private String districtName;
    @Column(name = "institution_name")
    private String institutionName;
    @Column(name = "type_id")
    private String typeId;
    @Column(name = "type")
    private String type;
    @Column(name = "affilating_university_id")
    private String affilatingUniversityId;
    @Column(name = "affilating_university_name")
    private String affilatingUniversityName;
    @Column(name = "nodal_officer_name")
    private String nodalOfficerName;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "status_name")
    private String statusName;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "amount")
    private Integer amount;

    @Transient
    private String submittedOn;
    @Column(name = "number_of_affiliating_college")
    private Integer numberOfAffiliatingCollege;
    @Column(name = "number_of_dcf_submitted_college")
    private Integer numberOfDcfSubmittedCollege;
    @Column(name = "is_this_new_university")
    private Boolean isThisNewUniversity;

    public String getSubmittedOn() {
        if (null != updatedOn) {
            return DateUtils.convertDBDateTimeToStringNew(updatedOn);
        }
        return submittedOn;
    }


}
