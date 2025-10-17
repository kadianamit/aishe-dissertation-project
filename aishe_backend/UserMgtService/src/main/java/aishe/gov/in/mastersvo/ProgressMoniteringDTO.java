package aishe.gov.in.mastersvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
@ToString
@Getter
@Setter
public class ProgressMoniteringDTO {
    private String formType;
    private String typeName;
    private String typeId;
    private BigInteger totalFormExpected;
    private BigInteger totalFormUploaded;

}
