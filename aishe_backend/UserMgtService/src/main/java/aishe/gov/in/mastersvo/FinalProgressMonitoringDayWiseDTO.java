package aishe.gov.in.mastersvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.BindingResult;

import java.math.BigInteger;
import java.util.Map;

@ToString
@Getter
@Setter
public class FinalProgressMonitoringDayWiseDTO {
    private String date;
    private BigInteger college;
    private BigInteger standalone;
    private BigInteger university;
}
