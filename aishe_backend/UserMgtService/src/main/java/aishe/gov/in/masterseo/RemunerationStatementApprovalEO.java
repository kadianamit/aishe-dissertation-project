package aishe.gov.in.masterseo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "remuneration_statement_approval")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemunerationStatementApprovalEO implements Serializable {

    private static final long serialVersionUID = 67321414153041797L;
    @EmbeddedId
    @Valid
    private RemunerationStatementApprovalKey approvalKey;
    @JsonIgnore
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "approver_id")
    private String approverUserId;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "remarks")
    private String remarks;
    @JsonIgnore
    @Column(name = "timestamp")
    private Timestamp timestamp;
}
