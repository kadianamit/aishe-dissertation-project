package com.nic.aishe.master.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "ref_teaching_staff_designation")
public class TeacherDesignation {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "valid_for_form1")
	private Boolean validForForm1;
	
	@Column(name = "valid_for_form2")
	private Boolean validForForm2;
	
	@Column(name = "valid_for_form3")
	private Boolean  validForForm3;
	
	@Column(name = "is_sanction_data")
	private Boolean  isSanctionData;

	@JsonIgnore
	@Column(name = "nature_of_appointment", columnDefinition = "jsonb")
	@Type(type = "jsonb")
	private List<Integer> natureOfAppointment;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Boolean getValidForForm1() {
		return validForForm1;
	}
	public void setValidForForm1(Boolean validForForm1) {
		this.validForForm1 = validForForm1;
	}
	public Boolean getValidForForm2() {
		return validForForm2;
	}
	public void setValidForForm2(Boolean validForForm2) {
		this.validForForm2 = validForForm2;
	}
	public Boolean getValidForForm3() {
		return validForForm3;
	}
	public void setValidForForm3(Boolean validForForm3) {
		this.validForForm3 = validForForm3;
	}
	public Boolean getIsSanctionData() {
		return isSanctionData;
	}
	public void setIsSanctionData(Boolean isSanctionData) {
		this.isSanctionData = isSanctionData;
	}

	public List<Integer> getNatureOfAppointment() {
		return natureOfAppointment;
	}

	public void setNatureOfAppointment(List<Integer> natureOfAppointment) {
		this.natureOfAppointment = natureOfAppointment;
	}

}
