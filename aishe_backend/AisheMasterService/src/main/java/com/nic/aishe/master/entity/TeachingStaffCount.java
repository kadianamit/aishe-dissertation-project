package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teaching_staff_count")
public class TeachingStaffCount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "teaching_staff_id")
	private TeachingStaff teachingStaff;
	
	@OneToOne
	@JoinColumn(name = "designation_id")
	private TeacherDesignation teacherDesignation;
	
	@Column(name = "grade_pay")
	private String gradePay;
	
	@OneToOne
	@JoinColumn(name = "selection_mode_id")
	private TeacherSelectionMode selectionMode;
	
	@OneToOne
	@JoinColumn(name = "count_by_category_id")
	private PersonCategoryCount personCategoryCount;


	public Integer getId() {
		return id;
	}


	public TeachingStaff getTeachingStaff() {
		return teachingStaff;
	}


	public void setTeachingStaff(TeachingStaff teachingStaff) {
		this.teachingStaff = teachingStaff;
	}


	public TeacherDesignation getTeacherDesignation() {
		return teacherDesignation;
	}


	public void setTeacherDesignation(TeacherDesignation teacherDesignation) {
		this.teacherDesignation = teacherDesignation;
	}


	public String getGradePay() {
		return gradePay;
	}


	public void setGradePay(String gradePay) {
		this.gradePay = gradePay;
	}


	public TeacherSelectionMode getSelectionMode() {
		return selectionMode;
	}


	public void setSelectionMode(TeacherSelectionMode selectionMode) {
		this.selectionMode = selectionMode;
	}


	public PersonCategoryCount getPersonCategoryCount() {
		return personCategoryCount;
	}


	public void setPersonCategoryCount(PersonCategoryCount personCategoryCount) {
		this.personCategoryCount = personCategoryCount;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
}