package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class SurveyStatusDTO {
    private Integer surveyYear;
    private String stateCode;
    private Boolean form1UploadDisabled;
    private Boolean form2UploadDisabled;
    private Boolean form3UploadDisabled;
}
