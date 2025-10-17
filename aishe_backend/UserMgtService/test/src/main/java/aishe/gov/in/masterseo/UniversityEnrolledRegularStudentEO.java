package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "university_enrolled_regular_student")
public class UniversityEnrolledRegularStudentEO {
	
	@Id
	@Column(name = "university_id")
	private String universityId;
	@Column(name = "survey_year")
	private Integer surveyYear;
	@ManyToOne
	@JoinColumn(name = "enrolled_regular_student_id")
	private EnrolledRegularStudentEO enrolledRegularStudent;
	
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
	public EnrolledRegularStudentEO getEnrolledRegularStudent() {
		return enrolledRegularStudent;
	}
	public void setEnrolledRegularStudent(EnrolledRegularStudentEO enrolledRegularStudent) {
		this.enrolledRegularStudent = enrolledRegularStudent;
	}
	
}
