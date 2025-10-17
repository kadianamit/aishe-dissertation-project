package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "public.survey_status_log")
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SurveyStatusLogEO {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "survey_year")
    private Integer surveyYear;
    @Column(name = "state_code")
    private String stateCode;
    @Column(name = "form1_upload_disabled")
    private Boolean form1UploadDisabled;
    @Column(name = "form2_upload_disabled")
    private Boolean form2UploadDisabled;
    @Column(name = "form3_upload_disabled")
    private Boolean form3UploadDisabled;
    @Column(name = "action_date")
    private Timestamp actionDate;
}
