package aishe.gov.in.mastersvo;

import aishe.gov.in.masterseo.RemunerationStatementDetailEO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Setter
@Getter
public class DetailVo {
   @Valid List<RemunerationStatementDetailEO> detailEOS;
}
