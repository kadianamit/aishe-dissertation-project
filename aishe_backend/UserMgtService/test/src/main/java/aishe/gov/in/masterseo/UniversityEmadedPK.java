package aishe.gov.in.masterseo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UniversityEmadedPK implements Serializable {

	@Column(name = "id")
	private String id;
	@Column(name = "survey_year")
	private Integer surveyYear;

	public UniversityEmadedPK() {

	}

	public UniversityEmadedPK(String id, Integer surveyYear) {
		this.id = id;
		this.surveyYear = surveyYear;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		if (!(o instanceof UniversityEmadedPK))
			return false;
		UniversityEmadedPK that = (UniversityEmadedPK) o;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getSurveyYear(), that.getSurveyYear());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getSurveyYear());
	}

}
