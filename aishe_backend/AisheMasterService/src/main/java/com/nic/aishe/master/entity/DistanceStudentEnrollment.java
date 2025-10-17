package com.nic.aishe.master.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="enrolled_distance_student_institute")
public class DistanceStudentEnrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "faculty_name",referencedColumnName = "name")
	private Faculty faculty;
	
	@OneToOne
	@JoinColumn(name = "department_name",referencedColumnName = "name")
	private Department department;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="enrolled_distance_student_institute_count",
	joinColumns =@JoinColumn(name="enrolled_distance_student_institute_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="enrolled_student_count_id",referencedColumnName = "id"))
	private List<StudentEnrollment> studentEnrollments;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<StudentEnrollment> getStudentEnrollments() {
		return studentEnrollments;
	}

	public void setStudentEnrollments(List<StudentEnrollment> studentEnrollments) {
		this.studentEnrollments = studentEnrollments;
	}
	
	
}