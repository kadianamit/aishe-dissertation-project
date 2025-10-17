package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemunerationStatementVo {
    @NotNull
    @Min(value = 0, message = "The value must be positive")
    private Integer approverRoleId;
    @NotBlank
    @NotNull
    private String approverUserId;
    @Min(value = 2010, message = "The value must be positive")
    private Integer surveyYear;
    @Valid
    private List<StatementGenerationVo> generation;
}