package aishe.gov.in.mastersvo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacultyTeacherDTO {

	@JsonProperty
	private String faculty;
	@JsonProperty
	private String department;
	@JsonProperty
	private List<TeacherDTO> teacher;
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
	public List<TeacherDTO> getTeacher() {
		return teacher;
	}
	public void setTeacher(List<TeacherDTO> teacher) {
		this.teacher = teacher;
	}
	
}
