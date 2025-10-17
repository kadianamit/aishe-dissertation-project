package aishe.gov.in.masterseo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "teacher")
public class Teacher {

	@Id
	@Column(name = "id")
	private Integer  id;
	@Column(name = "name")
	private String  name ;
	@Column(name = "mobile")
	private String  mobile;
	@Column(name = "email")
	private String  email;
	@Column(name = "is_pwd")
	private Boolean  isPwd;
	@Column(name = "nature_of_appointment")
	private String  natureOfAppointment;
	@Column(name = "selection_mode_id")
	private String  selectionMode ;
	@Column(name = "year_spend_other_than_teaching_job")
	private Integer  yearSpendOtherThanTeachingJob ;
	@Column(name = "highest_qualification")
	private String  highestQualification;
	@Column(name = "additional_qualification")
	private String  additionalQualification;
	@Column(name = "job_status")
	private String  jobStatus ;
	@Column(name = "date_of_birth")
	private Date  dateOfBirth;
	@Column(name = "date_of_joining_institution")
	private Date  dateOfJoiningInstitution;
	@Column(name = "date_of_change_in_job_status")
	private Date  dateOfChangeInJobStatus;
	@Column(name = "date_of_joining_teaching_profession")
	private Date  dateOfJoiningTeachingProfession;
	@Column(name = "aadhar_number")
	private String  aadharNumber;
	@ManyToOne
	@JoinColumn(name = "gender_id", insertable = false, updatable = false)
	private RefGender  gender;
	@ManyToOne
	@JoinColumn(name = "social_category_id", insertable = false, updatable = false)
	private RefSocialCategory  socialCategory;
	@ManyToOne
	@JoinColumn(name = "religious_community_id", insertable = false, updatable = false)
	private ReligiousCommunity  religiousCommunity;
	
	@ManyToOne
	@JoinColumn(name = "country_id", insertable = false, updatable = false)
	private RefCountry  country;
	@ManyToOne
	@JoinColumn(name = "broad_discipline_group_category_id", insertable = false, updatable = false)
	private RefBroadDisciplineGroupCategory  broadDisciplineGroupCategory;
	@ManyToOne
	@JoinColumn(name = "designation_id", insertable = false, updatable = false)
	private RefTeachingStaffDesignation  designation;
	@ManyToOne
	@JoinColumn(name = "broad_discipline_group_id", insertable = false, updatable = false)
	private RefBroadDisciplineGroup broadDisciplineGroup;
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
	public RefGender getGender() {
		return gender;
	}
	public void setGender(RefGender gender) {
		this.gender = gender;
	}
	public RefSocialCategory getSocialCategory() {
		return socialCategory;
	}
	public void setSocialCategory(RefSocialCategory socialCategory) {
		this.socialCategory = socialCategory;
	}
	public ReligiousCommunity getReligiousCommunity() {
		return religiousCommunity;
	}
	public void setReligiousCommunity(ReligiousCommunity religiousCommunity) {
		this.religiousCommunity = religiousCommunity;
	}
	
	public RefCountry getCountry() {
		return country;
	}
	public void setCountry(RefCountry country) {
		this.country = country;
	}
	public RefBroadDisciplineGroupCategory getBroadDisciplineGroupCategory() {
		return broadDisciplineGroupCategory;
	}
	public void setBroadDisciplineGroupCategory(RefBroadDisciplineGroupCategory broadDisciplineGroupCategory) {
		this.broadDisciplineGroupCategory = broadDisciplineGroupCategory;
	}
	public RefTeachingStaffDesignation getDesignation() {
		return designation;
	}
	public void setDesignation(RefTeachingStaffDesignation designation) {
		this.designation = designation;
	}
	public RefBroadDisciplineGroup getBroadDisciplineGroup() {
		return broadDisciplineGroup;
	}
	public void setBroadDisciplineGroup(RefBroadDisciplineGroup broadDisciplineGroup) {
		this.broadDisciplineGroup = broadDisciplineGroup;
	}
	
	
}
