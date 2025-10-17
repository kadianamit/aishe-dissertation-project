package aishe.gov.in.masterseo;

import aishe.gov.in.utility.UserAccountKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "public.user_account")
public class UserBankAccountEO {
    @EmbeddedId
    @Valid
    private UserAccountKey accountKey;
    @NotNull
    @NotBlank
    @Column(name = "account_holder_name")
    private String accountHolderName;
    @NotNull
    @NotBlank
    @Column(name = "account_Number")
    private String accountNumber;
    @NotNull
    @NotBlank
    @Column(name = "bank_address")
    private String bankAddress;
    @NotNull
    @NotBlank
    @Column(name = "bank_name")
    private String bankName;
    @NotNull
    @NotBlank
    @Column(name = "ifsc_code")
    private String ifsc_code;
    @JsonIgnore
    @Column(name = "updated_on")
    private Timestamp updatedOn;
    @JsonIgnore
    @Column(name = "ip_address")
    private String ipAddress;
    @NotNull
    @NotBlank
    @Size(min = 10, max = 10)
    @Column(name = "pan")
    private String pan;
    @NotNull
    @Column(name = "pin_code")
    private Integer pincode;
    @NotNull
    @NotBlank
    @Column(name = "aishe_code")
    private String aisheCode;

    @Column(name = "bank_document")
    private byte[] bankDocument;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "bank_document_name")
    private String bankDocumentName;

    @Transient
    private String institutionId;
    @Transient
    private String instituteType;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(CascadeType.REFRESH)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private RefRemunerationStatus status;

    @Transient
    private List<UserAccountLogEO> track;

    @Column(name = "number_of_affiliating_college")
    private Integer numberOfAffiliatingCollege;
    @Column(name = "number_of_dcf_submitted_college")
    private Integer numberOfDcfSubmittedCollege;
    @Column(name = "is_this_new_university")
    private Boolean isFirstTimeUploadDCFUniversity;

}
