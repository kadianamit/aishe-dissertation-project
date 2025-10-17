package aishe.gov.in.mastersvo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SurveyMasterDTO {

    @Min(1)
    @NotNull
    private Integer surveyYear;
    //@NotNull
    //@NotBlank
    private String startDate;
   // @NotNull
   // @NotBlank
    private String endDate;
    @NotNull
    @NotBlank
    private String userId;
    private Integer surveyCreationType;
    private String message;
}