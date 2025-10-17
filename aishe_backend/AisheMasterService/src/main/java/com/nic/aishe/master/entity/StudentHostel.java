package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "student_hostel")
public class StudentHostel {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "intake_capacity")
	private Integer capacity;

	private String name;

	@Column(name = "students_residing")
	private Integer resideStudent;
	
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="type_id",referencedColumnName = "id")
	private HostelType hostelType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getResideStudent() {
		return resideStudent;
	}

	public void setResideStudent(Integer resideStudent) {
		this.resideStudent = resideStudent;
	}

	public HostelType getHostelType() {
		return hostelType;
	}

	public void setHostelType(HostelType hostelType) {
		this.hostelType = hostelType;
	}



}
