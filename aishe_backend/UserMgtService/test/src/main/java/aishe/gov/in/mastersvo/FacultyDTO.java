package aishe.gov.in.mastersvo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacultyDTO {

	@JsonProperty
	private Integer id;
	@JsonProperty
	private String name;
	@JsonProperty
	private List<DepartmentDTO> department;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DepartmentDTO> getDepartment() {
		return department;
	}
	public void setDepartment(List<DepartmentDTO> department) {
		this.department = department;
	}
		
	
}
