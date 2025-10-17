package aishe.gov.in.mastersvo;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import aishe.gov.in.masterseo.RefSpecialityEO;


public class UniversityVO {

	private String aisheCode;
	private Integer surveyYear;
	private String name;	
	private Integer establishmentYear;
	private Integer yearWhenDeclaredUniversity;
	private NodalOfficerEO nodalOfficer;
	private Boolean girl_exclusive;	
	private Boolean specialized;	
	private Boolean haveStaffQuarter;
	private Boolean haveHostel;	
	private Integer noOfHostel;
	private String otherSpeciality;
	@ManyToOne
	@JoinColumn(name = "speciality_id")
	private RefSpecialityEO speciality;
	@ManyToOne
	@JoinColumn(name = "staff_quarter_id")
	private StaffQuarterEO staffQuarter;
	private List<StudentHostelEO> hostels;

	
	
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNoOfHostel() {
		return noOfHostel;
	}
	public void setNoOfHostel(Integer noOfHostel) {
		this.noOfHostel = noOfHostel;
	}
	public Integer getEstablishmentYear() {
		return establishmentYear;
	}
	public void setEstablishmentYear(Integer establishmentYear) {
		this.establishmentYear = establishmentYear;
	}
	public Integer getYearWhenDeclaredUniversity() {
		return yearWhenDeclaredUniversity;
	}
	public void setYearWhenDeclaredUniversity(Integer yearWhenDeclaredUniversity) {
		this.yearWhenDeclaredUniversity = yearWhenDeclaredUniversity;
	}
	public Boolean getSpecialized() {
		return specialized;
	}
	public void setSpecialized(Boolean specialized) {
		this.specialized = specialized;
	}
	public Boolean getGirl_exclusive() {
		return girl_exclusive;
	}
	public void setGirl_exclusive(Boolean girl_exclusive) {
		this.girl_exclusive = girl_exclusive;
	}
	public Boolean getHaveStaffQuarter() {
		return haveStaffQuarter;
	}
	public void setHaveStaffQuarter(Boolean haveStaffQuarter) {
		this.haveStaffQuarter = haveStaffQuarter;
	}
	public Boolean getHaveHostel() {
		return haveHostel;
	}
	public void setHaveHostel(Boolean haveHostel) {
		this.haveHostel = haveHostel;
	}
	public NodalOfficerEO getNodalOfficer() {
		return nodalOfficer;
	}
	public void setNodalOfficer(NodalOfficerEO nodalOfficer) {
		this.nodalOfficer = nodalOfficer;
	}
	public List<StudentHostelEO> getHostels() {
		return hostels;
	}
	public void setHostels(List<StudentHostelEO> hostels) {
		this.hostels = hostels;
	}
	public RefSpecialityEO getSpeciality() {
		return speciality;
	}
	public void setSpeciality(RefSpecialityEO speciality) {
		this.speciality = speciality;
	}
	public StaffQuarterEO getStaffQuarter() {
		return staffQuarter;
	}
	public void setStaffQuarter(StaffQuarterEO staffQuarter) {
		this.staffQuarter = staffQuarter;
	}
	public String getOtherSpeciality() {
		return otherSpeciality;
	}
	public void setOtherSpeciality(String otherSpeciality) {
		this.otherSpeciality = otherSpeciality;
	}
		
}
