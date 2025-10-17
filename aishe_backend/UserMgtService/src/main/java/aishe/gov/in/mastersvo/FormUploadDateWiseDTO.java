package aishe.gov.in.mastersvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
@ToString
@Getter
@Setter
public class FormUploadDateWiseDTO {
    private String date;
    private BigInteger totalCount;
}
