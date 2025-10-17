package org.nicexam.authorizationservice.requestDto;

import java.util.Date;

public class TeacherRequestDto {

	Long teacher_id;

	Long school_id;

	String udise_sch_code;

	String tch_code_state;

	String tch_code;

	String name;

	int gender;

	Date dob;

	int tch_type;

	int qual_acad;

	int qual_prof;

	int class_taught;

	int nature_of_appt;

	String email;

	String mobile;

	public int getNature_of_appt() {
		return nature_of_appt;
	}

	public void setNature_of_appt(int nature_of_appt) {
		this.nature_of_appt = nature_of_appt;
	}

	public Long getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Long teacher_id) {
		this.teacher_id = teacher_id;
	}

	public Long getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Long school_id) {
		this.school_id = school_id;
	}

	public String getUdise_sch_code() {
		return udise_sch_code;
	}

	public void setUdise_sch_code(String udise_sch_code) {
		this.udise_sch_code = udise_sch_code;
	}

	public String getTch_code_state() {
		return tch_code_state;
	}

	public void setTch_code_state(String tch_code_state) {
		this.tch_code_state = tch_code_state;
	}

	public String getTch_code() {
		return tch_code;
	}

	public void setTch_code(String tch_code) {
		this.tch_code = tch_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getTch_type() {
		return tch_type;
	}

	public void setTch_type(int tch_type) {
		this.tch_type = tch_type;
	}

	public int getQual_acad() {
		return qual_acad;
	}

	public void setQual_acad(int qual_acad) {
		this.qual_acad = qual_acad;
	}

	public int getQual_prof() {
		return qual_prof;
	}

	public void setQual_prof(int qual_prof) {
		this.qual_prof = qual_prof;
	}

	public int getClass_taught() {
		return class_taught;
	}

	public void setClass_taught(int class_taught) {
		this.class_taught = class_taught;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
