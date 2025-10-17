package org.nicexam.authorizationservice.usereo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.tch_profile_unique")
public class TeacherProfileUniqueEO {
	@Id
	@Column(name = "teacher_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "social_cat")
	int social_cat;

	@Column(name = "tch_type")
	int tch_type;

	@Column(name = "nature_of_appt")
	int nature_of_appt;

	@Column(name = "doj_service")
	Date doj_service;

	@Column(name = "qual_acad")
	int qual_acad;

	@Column(name = "qual_prof")
	int qual_prof;

	@Column(name = "class_taught")
	int class_taught;

	@Column(name = "appt_sub")
	int appt_sub;

	@Column(name = "sub_taught1")
	int sub_taught1;

	@Column(name = "sub_taught2")
	int sub_taught2;

	@Column(name = "trn_brc")
	int trn_brc;

	@Column(name = "trn_crc")
	int trn_crc;

	@Column(name = "trn_diet")
	int trn_diet;

	@Column(name = "trn_other")
	int trn_other;

	@Column(name = "trng_needed")
	int trng_needed;

	@Column(name = "nontch_days")
	int nontch_days;

	@Column(name = "math_upto")
	int math_upto;

	@Column(name = "science_upto")
	int science_upto;

	@Column(name = "english_upto")
	int english_upto;

	@Column(name = "soc_study_upto")
	int soc_study_upto;

	@Column(name = "yoj_pres_sch")
	String yoj_pres_sch;

	@Column(name = "disability_type")
	int disability_type;

	@Column(name = "trained_cwsn")
	int trained_cwsn;

	@Column(name = "trained_comp")
	int trained_comp;

	@Column(name = "email")
	String email;

	@Column(name = "mobile")
	String mobile;

	@Column(name = "trng_rcvd")
	int trng_rcvd;

	@Column(name = "lang_study_upto")
	int lang_study_upto;

	@Column(name = "is_migrated")
	boolean is_migrated;

	

//	public String getUdise_sch_code() {
//		return udise_sch_code;
//	}
//
//	public void setUdise_sch_code(String udise_sch_code) {
//		this.udise_sch_code = udise_sch_code;
//	}
//
//	public String getTch_code_state() {
//		return tch_code_state;
//	}
//
//	public Long getTeacher_id() {
//		return teacher_id;
//	}

	public String getUdise_sch_code() {
		return udise_sch_code;
	}

	public void setUdise_sch_code(String udise_sch_code) {
		this.udise_sch_code = udise_sch_code;
	}

	public Long getTeacher_id() {
		return teacher_id;
	}

	public String getTch_code_state() {
		return tch_code_state;
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

	public int getSocial_cat() {
		return social_cat;
	}

	public void setSocial_cat(int social_cat) {
		this.social_cat = social_cat;
	}

	public int getTch_type() {
		return tch_type;
	}

	public void setTch_type(int tch_type) {
		this.tch_type = tch_type;
	}

	public int getNature_of_appt() {
		return nature_of_appt;
	}

	public void setNature_of_appt(int nature_of_appt) {
		this.nature_of_appt = nature_of_appt;
	}

	public Date getDoj_service() {
		return doj_service;
	}

	public void setDoj_service(Date doj_service) {
		this.doj_service = doj_service;
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

	public int getAppt_sub() {
		return appt_sub;
	}

	public void setAppt_sub(int appt_sub) {
		this.appt_sub = appt_sub;
	}

	public int getSub_taught1() {
		return sub_taught1;
	}

	public void setSub_taught1(int sub_taught1) {
		this.sub_taught1 = sub_taught1;
	}

	public int getSub_taught2() {
		return sub_taught2;
	}

	public void setSub_taught2(int sub_taught2) {
		this.sub_taught2 = sub_taught2;
	}

	public int getTrn_brc() {
		return trn_brc;
	}

	public void setTrn_brc(int trn_brc) {
		this.trn_brc = trn_brc;
	}

	public int getTrn_crc() {
		return trn_crc;
	}

	public void setTrn_crc(int trn_crc) {
		this.trn_crc = trn_crc;
	}

	public int getTrn_diet() {
		return trn_diet;
	}

	public void setTrn_diet(int trn_diet) {
		this.trn_diet = trn_diet;
	}

	public int getTrn_other() {
		return trn_other;
	}

	public void setTrn_other(int trn_other) {
		this.trn_other = trn_other;
	}

	public int getTrng_needed() {
		return trng_needed;
	}

	public void setTrng_needed(int trng_needed) {
		this.trng_needed = trng_needed;
	}

	public int getNontch_days() {
		return nontch_days;
	}

	public void setNontch_days(int nontch_days) {
		this.nontch_days = nontch_days;
	}

	public int getMath_upto() {
		return math_upto;
	}

	public void setMath_upto(int math_upto) {
		this.math_upto = math_upto;
	}

	public int getScience_upto() {
		return science_upto;
	}

	public void setScience_upto(int science_upto) {
		this.science_upto = science_upto;
	}

	public int getEnglish_upto() {
		return english_upto;
	}

	public void setEnglish_upto(int english_upto) {
		this.english_upto = english_upto;
	}

	public int getSoc_study_upto() {
		return soc_study_upto;
	}

	public void setSoc_study_upto(int soc_study_upto) {
		this.soc_study_upto = soc_study_upto;
	}

	public String getYoj_pres_sch() {
		return yoj_pres_sch;
	}

	public void setYoj_pres_sch(String yoj_pres_sch) {
		this.yoj_pres_sch = yoj_pres_sch;
	}

	public int getDisability_type() {
		return disability_type;
	}

	public void setDisability_type(int disability_type) {
		this.disability_type = disability_type;
	}

	public int getTrained_cwsn() {
		return trained_cwsn;
	}

	public void setTrained_cwsn(int trained_cwsn) {
		this.trained_cwsn = trained_cwsn;
	}

	public int getTrained_comp() {
		return trained_comp;
	}

	public void setTrained_comp(int trained_comp) {
		this.trained_comp = trained_comp;
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

	public int getTrng_rcvd() {
		return trng_rcvd;
	}

	public void setTrng_rcvd(int trng_rcvd) {
		this.trng_rcvd = trng_rcvd;
	}

	public int getLang_study_upto() {
		return lang_study_upto;
	}

	public void setLang_study_upto(int lang_study_upto) {
		this.lang_study_upto = lang_study_upto;
	}

	public boolean isIs_migrated() {
		return is_migrated;
	}

	public void setIs_migrated(boolean is_migrated) {
		this.is_migrated = is_migrated;
	}

	
}
