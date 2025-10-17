package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.university_off_shore_center")
public class UniversityOffShoreEO {

	@Id
	@Column(name = "university_id")
	private String universityId;
	@Column(name = "off_shore_center_id",updatable=true)
	private Integer offShoreCenterId;
	@Column(name = "survey_year")
	private Integer surveyYear;
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public Integer getOffShoreCenterId() {
		return offShoreCenterId;
	}
	public void setOffShoreCenterId(Integer offShoreCenterId) {
		this.offShoreCenterId = offShoreCenterId;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

}