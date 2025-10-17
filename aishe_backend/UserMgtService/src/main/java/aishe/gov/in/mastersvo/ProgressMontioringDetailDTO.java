package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgressMontioringDetailDTO {
    private String stateCode;
    private String stateName;
    private BigInteger universityExpectedForm;
    private BigInteger universitySubmitForm;
    private BigInteger collegeExpectedForm;
    private BigInteger collegeSubmitForm;
    private BigInteger standaloneExpectedForm;
    private BigInteger standaloneSubmitForm;
}
