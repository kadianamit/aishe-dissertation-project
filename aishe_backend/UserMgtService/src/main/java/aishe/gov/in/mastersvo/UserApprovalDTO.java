package aishe.gov.in.mastersvo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserApprovalDTO {
    @NotBlank
    @NotNull
    private String userId;
    @NotBlank
    @NotNull
    private  String approvedBy;
    @NotNull
    private  Boolean isApproval;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getApproval() {
        return isApproval;
    }

    public void setApproval(Boolean approval) {
        isApproval = approval;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
