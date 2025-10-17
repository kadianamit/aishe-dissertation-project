package aishe.gov.in.mastersvo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacultyProgramDTO {
	
	@JsonProperty
	private String facultyName;
	List<ProgramDTO> programs;
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public List<ProgramDTO> getPrograms() {
		return programs;
	}
	public void setPrograms(List<ProgramDTO> programs) {
		this.programs = programs;
	}
	
	

}
