package aishe.gov.in.masterseo;

import aishe.gov.in.utility.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "remuneration_statement_approval")
public class ApprovalStatus implements Serializable {

    private static final long serialVersionUID = 67321414153041797L;

    @Column(name = "form_upload_id")
    private Integer formUploadId;
    @Column(name = "approver_role_id")
    private Integer approverRoleId;
    @Id
    @Column(name = "role_name")
    private String approvalRoleName;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "status_name")
    private String statusName;
    @Column(name = "remarks")
    private String remarks;
    @JsonIgnore
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Transient
    private String approvalDateTime;

    public String getApprovalDateTime() {
        if (null != timestamp)
            return DateUtils.convertDBDateTimeToString(timestamp);
        return null;
    }

}
