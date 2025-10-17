package org.nicexam.authorizationservice.usereo;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.tch_profile_history")
public class TeacherProfileHistoryEO {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "teacher_id")
	Long teacher_id;

	@Column(name = "school_id")
	Long school_id;

	@Column(name = "udise_sch_code")
	String udise_sch_code;

	@Column(name = "tch_code_state")
	String tch_code_state;

	@Column(name = "tch_code")
	String tch_code;

	@Column(name = "name")
	String name;

	@Column(name = "gender")
	int gender;

	@Column(name = "dob")
	Date dob;

//	@Column(name = "social_cat")
//	int social_cat;
//
	@Column(name = "tch_type")
	int tch_type;

	@Column(name = "nature_of_appt")
	int nature_of_appt;

//	@Column(name = "doj_service")
//	Date doj_service;

	@Column(name = "qual_acad")
	int qual_acad;

	@Column(name = "qual_prof")
	int qual_prof;

	@Column(name = "class_taught")
	int class_taught;

//	@Column(name = "appt_sub")
//	int appt_sub;
//
//	@Column(name = "sub_taught1")
//	int sub_taught1;
//
//	@Column(name = "sub_taught2")
//	int sub_taught2;
//
//	@Column(name = "trn_brc")
//	int trn_brc;
//
//	@Column(name = "trn_crc")
//	int trn_crc;
//
//	@Column(name = "trn_diet")
//	int trn_diet;
//
//	@Column(name = "trn_other")
//	int trn_other;
//
//	@Column(name = "trng_needed")
//	int trng_needed;

//	@Column(name = "nontch_days")
//	int nontch_days;

//	@Column(name = "math_upto")
//	int math_upto;
//
//	@Column(name = "science_upto")
//	int science_upto;
//
//	@Column(name = "english_upto")
//	int english_upto;
//
//	@Column(name = "soc_study_upto")
//	int soc_study_upto;

//	@Column(name = "yoj_pres_sch")
//	String yoj_pres_sch;
//
//	@Column(name = "disability_type")
//	int disability_type;

//	@Column(name = "trained_cwsn")
//	int trained_cwsn;
//
//	@Column(name = "trained_comp")
//	int trained_comp;

	public int getNature_of_appt() {
		return nature_of_appt;
	}

	public void setNature_of_appt(int nature_of_appt) {
		this.nature_of_appt = nature_of_appt;
	}

	@Column(name = "email")
	String email;

	@Column(name = "mobile")
	String mobile;

	@Column(name = "approved_by")
	String approved_by;

//	@Column(name = "trng_rcvd")
//	int trng_rcvd;
//
//	@Column(name = "lang_study_upto")
//	int lang_study_upto;

//	@Column(name = "is_migrated")
//	boolean is_migrated;

	@Column(name = "login_id")
	String loginId;

	@Column(name = "random_String")
	String randomString;

	@Column(name = "status")
	String status;

	@Column(name = "remarks")
	String remarks;

	@Column(name = "created_by")
	Long createdBy;

	@Column(name = "created_on")
	LocalDateTime createdOn;

	@Column(name = "updated_by")
	Long updatedBy;

	@Column(name = "updated_on")
	LocalDateTime updatedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getRandomString() {
		return randomString;
	}

	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}

}
