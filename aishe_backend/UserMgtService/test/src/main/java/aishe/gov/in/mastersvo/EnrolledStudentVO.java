package aishe.gov.in.mastersvo;

import java.util.List;

public class EnrolledStudentVO {
	
	private String facultyName;
	private String departmentName;
	List<EnrolledStudentDetailVO> enrolledStudentDetail;
	
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<EnrolledStudentDetailVO> getEnrolledStudentDetail() {
		return enrolledStudentDetail;
	}
	public void setEnrolledStudentDetail(List<EnrolledStudentDetailVO> enrolledStudentDetail) {
		this.enrolledStudentDetail = enrolledStudentDetail;
	}
	
	
	

}
