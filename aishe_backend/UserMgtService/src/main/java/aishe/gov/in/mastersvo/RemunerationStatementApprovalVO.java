package aishe.gov.in.mastersvo;

import aishe.gov.in.masterseo.RemunerationStatementApprovalEO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Setter
@Getter
public class RemunerationStatementApprovalVO {
   @Valid List<RemunerationStatementApprovalEO> approval;
}
