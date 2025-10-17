package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class SurveyStatusLogDTO {
    private Integer surveyYear;
    private String state;
    private String actionDate;
    private String userId;
    private Boolean form1UploadDisabled;
    private Boolean form2UploadDisabled;
    private Boolean form3UploadDisabled;
}
