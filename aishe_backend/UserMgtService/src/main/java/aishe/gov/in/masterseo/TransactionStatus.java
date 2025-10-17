package aishe.gov.in.masterseo;

import aishe.gov.in.utility.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Transient;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "remuneration_statement_detail")
public class TransactionStatus {
    @Id
    @Column(name = "form_upload_id")
    private Integer formUploadId;
    @Column(name = "statement_id")
    private String statementId;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "transaction_status_id")
    private Integer transactionStatusId;
    @Column(name = "transaction_status")
    private String transactionStatus;
    @JsonIgnore
    @Column(name = "transaction_timestamp")
    private LocalDateTime transactionTimestamp;
    @Column(name = "transaction_updater_ip_address")
    private String transactionUpdaterIpAddress;
    @Transient
    private String transactionDateTime;

    public String getTransactionDateTime() {
        if (null != transactionTimestamp)
            return DateUtils.convertDBDateTimeToString(transactionTimestamp);
        return null;
    }
}
