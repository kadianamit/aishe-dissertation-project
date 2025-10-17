package com.nic.aishe.master.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="teacher")
public class Teacher implements Serializable{

	private static final long serialVersionUID = 8912302487854950150L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;
	
	@Column(name="name")
	private String  name;
	
	@Column(name="mobile")
	private String  mobile;
	
	@Column(name="email")
	private String  email;
	
	@Column(name="is_pwd")
	private Boolean  isPwd;
	
	@Column(name="nature_of_appointment")
	private String  natureOfAppointment;
	
	@Column(name="selection_mode_id")
	private String  selectionMode ;
	
	@Column(name="year_spend_other_than_teaching_job")
	private Integer  yearSpendOtherThanTeachingJob ;
	
	@Column(name="highest_qualification")
	private String  highestQualification;
	
	@Column(name="additional_qualification")
	private String  additionalQualification;
	
	@Column(name="job_status")
	private String  jobStatus ;	  
	
	@Column(name="date_of_birth")
	private Date  dateOfBirth;
	
	@Column(name="date_of_joining_institution")
	private Date  dateOfJoiningInstitution;
	
	@Column(name="date_of_change_in_job_status")
	private Date  dateOfChangeInJobStatus;
	
	@Column(name="date_of_joining_teaching_profession")
	private Date  dateOfJoiningTeachingProfession;
	
	@Column(name="aadhar_number")
	private String  aadharNumber;
	
	@OneToOne
	@JoinColumn(name="gender_id")
	private Gender  gender;

	@OneToOne
	@JoinColumn(name="social_category_id")
	private SocialCategory  socialCategory;

	@OneToOne
	@JoinColumn(name="religious_community_id")
	private ReligiousCommunity  religiousCommunity;

	@OneToOne
	@JoinColumn(name="teaching_staff_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private TeachingStaff  teachingStaff;
//	@Column(name="teaching_staff_id")
//	private Integer teacherId;
	

	@OneToOne
	@JoinColumn(name="country_id")
	private Country  country;
		
//	@Column(name="broad_discipline_group_category_id")
//	private Integer  broadDisciplineGroupCategory;
	
	@OneToOne
	@JoinColumn(name="broad_discipline_group_category_id")
	private BroadCategory  broadCategory;
	

	@OneToOne
	@JoinColumn(name="designation_id")
	private TeacherDesignation  designation;
	

	@OneToOne
	@JoinColumn(name="broad_discipline_group_id")
	private BroadName broadDisciplineGroup;
	
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsPwd() {
		return isPwd;
	}
	public void setIsPwd(Boolean isPwd) {
		this.isPwd = isPwd;
	}
	public String getNatureOfAppointment() {
		return natureOfAppointment;
	}
	public void setNatureOfAppointment(String natureOfAppointment) {
		this.natureOfAppointment = natureOfAppointment;
	}
	public String getSelectionMode() {
		return selectionMode;
	}
	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}
	public Integer getYearSpendOtherThanTeachingJob() {
		return yearSpendOtherThanTeachingJob;
	}
	public void setYearSpendOtherThanTeachingJob(Integer yearSpendOtherThanTeachingJob) {
		this.yearSpendOtherThanTeachingJob = yearSpendOtherThanTeachingJob;
	}
	public String getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	public String getAdditionalQualification() {
		return additionalQualification;
	}
	public void setAdditionalQualification(String additionalQualification) {
		this.additionalQualification = additionalQualification;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfJoiningInstitution() {
		return dateOfJoiningInstitution;
	}
	public void setDateOfJoiningInstitution(Date dateOfJoiningInstitution) {
		this.dateOfJoiningInstitution = dateOfJoiningInstitution;
	}
	public Date getDateOfChangeInJobStatus() {
		return dateOfChangeInJobStatus;
	}
	public void setDateOfChangeInJobStatus(Date dateOfChangeInJobStatus) {
		this.dateOfChangeInJobStatus = dateOfChangeInJobStatus;
	}
	public Date getDateOfJoiningTeachingProfession() {
		return dateOfJoiningTeachingProfession;
	}
	public void setDateOfJoiningTeachingProfession(Date dateOfJoiningTeachingProfession) {
		this.dateOfJoiningTeachingProfession = dateOfJoiningTeachingProfession;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public SocialCategory getSocialCategory() {
		return socialCategory;
	}
	public void setSocialCategory(SocialCategory socialCategory) {
		this.socialCategory = socialCategory;
	}
	public ReligiousCommunity getReligiousCommunity() {
		return religiousCommunity;
	}
	public void setReligiousCommunity(ReligiousCommunity religiousCommunity) {
		this.religiousCommunity = religiousCommunity;
	}
//	public TeachingStaff getTeachingStaff() {
//		return teachingStaff;
//	}
//	public void setTeachingStaff(TeachingStaff teachingStaff) {
//		this.teachingStaff = teachingStaff;
//	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}

	public TeacherDesignation getDesignation() {
		return designation;
	}
	public BroadCategory getBroadCategory() {
		return broadCategory;
	}
	public void setBroadCategory(BroadCategory broadCategory) {
		this.broadCategory = broadCategory;
	}
	public void setDesignation(TeacherDesignation designation) {
		this.designation = designation;
	}
	public BroadName getBroadDisciplineGroup() {
		return broadDisciplineGroup;
	}
	public void setBroadDisciplineGroup(BroadName broadDisciplineGroup) {
		this.broadDisciplineGroup = broadDisciplineGroup;
	}
	public TeachingStaff getTeachingStaff() {
		return teachingStaff;
	}
	public void setTeachingStaff(TeachingStaff teachingStaff) {
		this.teachingStaff = teachingStaff;
	}

	
	
	
}
