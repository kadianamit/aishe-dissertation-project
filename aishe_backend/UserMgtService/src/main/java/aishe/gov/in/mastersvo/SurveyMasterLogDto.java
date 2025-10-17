package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class SurveyMasterLogDto {

    private Integer surveyYear;

    private String startDate;

    private String endDate;

    private String userId;

    private String action;

    private String actionDate;
}
