package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.university_hostel")
public class UniversityHostelEO {
	
	@Id
	@Column(name = "university_id")
	private String universityId;
	@Column(name = "student_hostel_id")
	private Integer studentHostelId;
	@Column(name = "survey_year")
	private Integer surveyYear;
	
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public Integer getStudentHostelId() {
		return studentHostelId;
	}
	public void setStudentHostelId(Integer studentHostelId) {
		this.studentHostelId = studentHostelId;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	
	

}
