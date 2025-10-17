package aishe.gov.in.masterseo;

import aishe.gov.in.utility.SurveyStatusKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "public.survey_status")
@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class SurveyStatusEO{
    @EmbeddedId
    private SurveyStatusKey statusKey;
   // @Id
   // @Column(name="survey_year")
   // private Integer surveyYear;
   // @Column(name="state_code")
   // private String stateCode;
    @Column(name = "form1_upload_disabled")
    private Boolean form1UploadDisabled;
    @Column(name = "form2_upload_disabled")
    private Boolean form2UploadDisabled;
    @Column(name = "form3_upload_disabled")
    private Boolean form3UploadDisabled;
}
