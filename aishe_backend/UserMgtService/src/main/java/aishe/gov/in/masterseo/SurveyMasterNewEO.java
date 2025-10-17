package aishe.gov.in.masterseo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "public.survey_master_new")
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SurveyMasterNewEO {
    @Id
    @Min(0)
    @Column(name = "survey_year")
    private Integer surveyYear;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
    
    @Column(name = "registration_start_date")
    private LocalDateTime registrationStartDate;
    
    @Column(name = "registration_end_date")
    private LocalDateTime registrationEndDate;
    
    @Column(name = "survey_start_date")
    private LocalDateTime surveyStartDate;
    
    @Column(name = "survey_end_date")
    private LocalDateTime surveyEndDate;
    
    @Column(name = "special_permission_start_date")
    private LocalDateTime specialPermissionStartDate;
    
    @Column(name = "special_permission_end_date")
    private LocalDateTime specialPermissionEndDate;
    
    @Column(name = "is_survey_freezed")
    private Boolean isSurveyFreezed;
    //@NotNull
    //@NotBlank
    @Transient
    private String userId;
}