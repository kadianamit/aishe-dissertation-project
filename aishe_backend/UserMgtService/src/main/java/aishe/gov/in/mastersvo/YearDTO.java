package aishe.gov.in.mastersvo;

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
public class YearDTO {
    private Integer surveyYear;
    private Integer startYear;
    private Integer endYear;
}
