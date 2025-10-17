package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "public.faculty_department")
public class FacultyDepartment {
	
	@Id
	@Column(name = "faculty_id")
	private Integer facultyId;
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
	public Integer getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	

}
