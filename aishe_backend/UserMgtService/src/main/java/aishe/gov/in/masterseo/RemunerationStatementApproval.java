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
import java.time.LocalDateTime;
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "remuneration_statement_approval")
public class RemunerationStatementApproval {
    @Id
    @Column(name = "form_upload_id")
    private Integer formUploadId;
    @Column(name = "approver_role_id")
    private Integer approverRoleId;
    @Column(name = "approver_id")
    private String approver_id;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "ip_address")
    private String ipAddress;
}
