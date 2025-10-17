package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "data_sharing.user_request_approval_details")
public class UserRequestApprovalDetailsEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "request_id")
    private String requestId;
    @Column(name = "approver_id")
    private String approverId;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "approved_date")
    private LocalDateTime approvedDate;
    private String remarks;

    public UserRequestApprovalDetailsEO(String requestId, String approverId, Integer statusId, String ipAddress, LocalDateTime approvedDate, String remarks) {
        this.requestId = requestId;
        this.approverId = approverId;
        this.statusId = statusId;
        this.ipAddress = ipAddress;
        this.approvedDate = approvedDate;
        this.remarks = remarks;
    }
}
