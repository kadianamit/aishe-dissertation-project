package aishe.gov.in.masterseo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "remuneration_statement_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemunerationStatementDetailEO {
    @NotBlank
    @NotNull
    @Column(name = "statement_id")
    private String statementId;
    @Column(name = "transaction_updater_user_id")
    private String approverUserId;
    @JsonIgnore
    @Column(name = "transaction_timestamp")
    private Timestamp timestamp;
    @JsonIgnore
    @Column(name = "transaction_updater_ip_address")
    private String ipAddress;
    @Id
    @Column(name = "form_upload_id")
    private Integer formUploadId;

    private String remarks;
    @Column(name = "transaction_status_id")
    private Integer transactionStatusId;

    @Email
    @Transient
    private String emailId;
}