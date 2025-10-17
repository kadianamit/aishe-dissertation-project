package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "remuneration_statement_approval_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemunerationStatementApprovalLogEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "form_upload_id")
    private Integer formUploadId;
    @Column(name = "user_role_id")
    private Integer approverRoleId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "ip_address")
    private String ipAddress;
}
