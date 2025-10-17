package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatementGenerationVo {
    @NotNull
    @Min(value = 0, message = "The value must be positive")
    private Integer formUploadId;
    @NotNull
    @Min(value = 0, message = "The value must be positive")
    private Integer noOfProgramme;

    private Boolean constitutedFromColleges;
    @NotNull
    @Min(value = 0, message = "The value must be positive")
    private BigInteger amount;
    @NotNull
    @Min(value = 0, message = "The value must be positive")
    private Integer normTypeId;
}