package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "public.survey_master")
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SurveyMaster {
    @Id
    @Min(1)
    @Column(name = "survey_year")
    private Integer surveyYear;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;
    @NotNull
    @NotBlank
    @Transient
    private String userId;

}
