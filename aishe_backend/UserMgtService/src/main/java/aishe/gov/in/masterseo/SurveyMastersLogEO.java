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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "public.survey_master_log")
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SurveyMastersLogEO {
    @Id
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "survey_year")
    private Integer surveyYear;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "prev_end_date")
    private LocalDateTime prevEndDate;
    @Column(name = "prev_start_date")
    private LocalDateTime prevStartDate;
    @Column(name = "action_date")
    private Timestamp actionDate;
    @ManyToOne
    @JoinColumn(name = "action_id")
    private RefSurveyAction actionId;
}
