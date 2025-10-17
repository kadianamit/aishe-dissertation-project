package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class SurveyStatusWithUserStatusDTO {
    @Min(1)
    private Integer surveyYear;
    @NotNull
    private String stateCode;
    @NotNull
    @NotBlank
    private String userId;
    @NotNull
    private Boolean form1UploadDisabled;
    @NotNull
    private Boolean form2UploadDisabled;
    @NotNull
    private Boolean form3UploadDisabled;
}
