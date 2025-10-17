package aishe.gov.in.mastersvo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentProgramDTO {
	
	@JsonProperty
	private String departmentName;
	List<ProgramDTO> programs;
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<ProgramDTO> getPrograms() {
		return programs;
	}
	public void setPrograms(List<ProgramDTO> programs) {
		this.programs = programs;
	}
	
	

}
