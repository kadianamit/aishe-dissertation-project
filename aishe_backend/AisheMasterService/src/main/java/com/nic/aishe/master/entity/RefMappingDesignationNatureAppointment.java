package com.nic.aishe.master.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ref_mapping_designation_natureappointment")
public class RefMappingDesignationNatureAppointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="nature_of_appointment_id", referencedColumnName = "id")
	private AppointmentNature appointmentNature;
	
	@OneToOne
	@JoinColumn(name="teaching_staff_designation_id", referencedColumnName = "id")
	private TeacherDesignation teacherDesignation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AppointmentNature getAppointmentNature() {
		return appointmentNature;
	}

	public void setAppointmentNature(AppointmentNature appointmentNature) {
		this.appointmentNature = appointmentNature;
	}

	public TeacherDesignation getTeacherDesignation() {
		return teacherDesignation;
	}

	public void setTeacherDesignation(TeacherDesignation teacherDesignation) {
		this.teacherDesignation = teacherDesignation;
	}
	
	
}
