package aishe.gov.in.mastersvo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RemunerationChangeStatus {
    @NotBlank
    @NotNull
    private String aisheCode;
    @NotBlank
    @NotNull
    private String userId;

    @NotNull
    private Integer surveyYear;
    @NotNull
    private Integer status;

    private String remarks;
}
