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
@Table(name = "teaching_staff_sanctioned_strength")
public class TeachingSactionStrength {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name="designation_id")
	private TeacherDesignation teacherDesignation;
	
	@Column(name = "sanctioned_strength")
	private Integer sanctionedStrength;
	
	@Column(name = "in_position")
	private Integer inPosition;
	
	@Column(name = "in_position_direct")
	private Integer inPositionDirect;
	
	@Column(name = "in_position_cas")
	private Integer inPositionCas;
	
	@Column(name = "no_of_phd_teachers")
	private Integer phdTeachers;

	public Integer getId() {
		return id;
	}

	public TeacherDesignation getTeacherDesignation() {
		return teacherDesignation;
	}

	public void setTeacherDesignation(TeacherDesignation teacherDesignation) {
		this.teacherDesignation = teacherDesignation;
	}

	public Integer getSanctionedStrength() {
		return sanctionedStrength;
	}

	public void setSanctionedStrength(Integer sanctionedStrength) {
		this.sanctionedStrength = sanctionedStrength;
	}

	public Integer getInPosition() {
		return inPosition;
	}

	public void setInPosition(Integer inPosition) {
		this.inPosition = inPosition;
	}

	public Integer getInPositionDirect() {
		return inPositionDirect;
	}

	public void setInPositionDirect(Integer inPositionDirect) {
		this.inPositionDirect = inPositionDirect;
	}

	public Integer getInPositionCas() {
		return inPositionCas;
	}

	public void setInPositionCas(Integer inPositionCas) {
		this.inPositionCas = inPositionCas;
	}

	public Integer getPhdTeachers() {
		return phdTeachers;
	}

	public void setPhdTeachers(Integer phdTeachers) {
		this.phdTeachers = phdTeachers;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}