package aishe.gov.in.mastersvo;

import aishe.gov.in.masterseo.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RemunerationProcessStatusVO {
    private String dcfUploadStatus;
    private String bankAccountStatus;
   // private String approvalStatus;
    private String transactionStatus;
    private List<ApprovalStatus> approvalStatusList;
}
