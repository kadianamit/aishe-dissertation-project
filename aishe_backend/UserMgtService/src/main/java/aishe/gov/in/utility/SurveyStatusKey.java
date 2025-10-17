package aishe.gov.in.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class SurveyStatusKey implements Serializable {
    @Column(name = "survey_year")
    private Integer surveyYear;
    @Column(name = "state_code")
    private String stateCode;


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SurveyStatusKey))
            return false;
        SurveyStatusKey that = (SurveyStatusKey) o;
        return Objects.equals(getStateCode(), that.getStateCode()) && Objects.equals(getSurveyYear(), that.getSurveyYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStateCode(), getSurveyYear());
    }
}
