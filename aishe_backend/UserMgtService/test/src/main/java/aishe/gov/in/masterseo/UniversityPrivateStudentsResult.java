package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "university_private_students_result")
public class UniversityPrivateStudentsResult {

	@Id
	@Column(name = "university_id")
	private String universityId;
	@Column(name = "survey_year")
	private Integer surveyYear;
	@ManyToOne
	@JoinColumn(name = "private_students_result_id", insertable = false, updatable = false)
	private PrivateStudentsResult privateStudentsResult;
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	public PrivateStudentsResult getPrivateStudentsResult() {
		return privateStudentsResult;
	}
	public void setPrivateStudentsResult(PrivateStudentsResult privateStudentsResult) {
		this.privateStudentsResult = privateStudentsResult;
	}
	
	
}
