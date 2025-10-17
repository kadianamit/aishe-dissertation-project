package aishe.gov.in.mastersvo;

import aishe.gov.in.masterseo.RemunerationStatementDetailWithAmountEO;
import aishe.gov.in.masterseo.RemunerationStatementEO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class RemunerationStatementDetailVO {
    private RemunerationStatementEO statement;
    private List<RemunerationStatementDetailWithAmountEO> details;
}
