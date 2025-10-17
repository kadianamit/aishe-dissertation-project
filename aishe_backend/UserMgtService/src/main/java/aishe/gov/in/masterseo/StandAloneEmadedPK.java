package aishe.gov.in.masterseo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StandAloneEmadedPK implements Serializable {

	@Column(name = "id")
	private Integer id;
	@Column(name = "survey_year")
	private Integer surveyYear;

	public StandAloneEmadedPK() {

	}

	public StandAloneEmadedPK(Integer id, Integer surveyYear) {
		this.id = id;
		this.surveyYear = surveyYear;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof StandAloneEmadedPK))
			return false;
		StandAloneEmadedPK that = (StandAloneEmadedPK) o;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getSurveyYear(), that.getSurveyYear());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getSurveyYear());
	}

}
