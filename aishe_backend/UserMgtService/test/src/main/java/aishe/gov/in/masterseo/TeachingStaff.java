package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

import aishe.gov.in.jsonbutility.JsonUserType;

@Entity
@Table(name = "teaching_staff")
@TypeDef(name = "JsonUserType", typeClass = JsonUserType.class)
public class TeachingStaff {

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "faculty_name")
	private String facultyName;
	@Column(name = "department_name")
	private String departmentName;
	
	@Column(name = "econtact")
	private String econtact;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getEcontact() {
		return econtact;
	}
	public void setEcontact(String econtact) {
		this.econtact = econtact;
	}
	
	
	
}
