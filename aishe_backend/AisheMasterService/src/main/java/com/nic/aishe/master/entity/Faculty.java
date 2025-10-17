package com.nic.aishe.master.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="faculty")
public class Faculty implements Serializable{
	
	private static final long serialVersionUID = -8751165222467810833L;
	@Id	
	private Integer id;
	private String name;
	@Column(name="new_id")
	private Integer newId;

	@OneToMany(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinTable(name="faculty_department",
	joinColumns =@JoinColumn(name="faculty_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="department_id",referencedColumnName = "id"))
	private List<Department> departments;

//	@OneToMany(fetch = FetchType.LAZY)
//	@NotFound(action = NotFoundAction.IGNORE)
//	@JoinTable(name="faculty_teaching_staff_count",
//	joinColumns =@JoinColumn(name="faculty_id",referencedColumnName = "id"),
//	inverseJoinColumns = @JoinColumn(name="teaching_staff_count_id",referencedColumnName = "id"))
//	private List<TeachingStaffCount> teachingStaffCounts;
	
/*	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinTable(name = "college_institution_faculty", joinColumns = {
			@JoinColumn(name = "faculty_id", referencedColumnName = "id")}, 
	inverseJoinColumns = {
	@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
	@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") })
	private CollegeInfo collegeInfo;*/
	
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
	public List<Department> listFacultyDepartments() {
		return departments;
	}
	public void addDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Integer getNewId() {
		return newId;
	}

	public void setNewId(Integer newId) {
		this.newId = newId;
	}
	//	public List<TeachingStaffCount> listTeachingStaffCounts() {
//		return teachingStaffCounts;
//	}
//	public void addTeachingStaffCounts(List<TeachingStaffCount> teachingStaffCounts) {
//		this.teachingStaffCounts = teachingStaffCounts;
//	}
	
	
}
