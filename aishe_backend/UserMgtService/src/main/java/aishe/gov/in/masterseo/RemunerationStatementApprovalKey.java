package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class RemunerationStatementApprovalKey implements Serializable {

    private static final long serialVersionUID = 67321414153041797L;
    @Min(0)
    @Column(name = "form_upload_id")
    private Integer formUploadId;
    @Min(0)
    @Column(name = "approver_role_id")
    private Integer approverRoleId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RemunerationStatementApprovalKey))
            return false;
        RemunerationStatementApprovalKey that = (RemunerationStatementApprovalKey) o;
        return Objects.equals(getFormUploadId(), that.getFormUploadId()) && Objects.equals(getApproverRoleId(), that.getApproverRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFormUploadId(), getApproverRoleId());
    }
}
