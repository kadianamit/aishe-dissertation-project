package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;

@ToString
@Getter
@Setter
public class ProgressMonitoringBasicDTO {
    private BigInteger totalBasicFilled;
    private BigInteger totalFormUploaded;
    @JsonIgnore
    private List<FormUploadDateWiseDTO> dayWise;

}
