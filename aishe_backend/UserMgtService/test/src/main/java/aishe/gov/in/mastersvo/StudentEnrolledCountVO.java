package aishe.gov.in.mastersvo;

import java.util.List;

public class StudentEnrolledCountVO {
	private String aisheCode;
	private Integer surveyYear;
	private Integer id;
	private String faculty;
	private String department;
	private EnrolledStudentCountEO studentEnrollments;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public EnrolledStudentCountEO getStudentEnrollments() {
		return studentEnrollments;
	}
	public void setStudentEnrollments(EnrolledStudentCountEO studentEnrollments) {
		this.studentEnrollments = studentEnrollments;
	}
	
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
	
}
