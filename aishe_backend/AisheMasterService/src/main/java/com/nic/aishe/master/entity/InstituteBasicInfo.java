package com.nic.aishe.master.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="institution_details")
public class InstituteBasicInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="institution_id")
	private Integer institution_id;
	
	@Column(name="aishe_code")
	private String aisheCode;
	
	private String name;
	
	@Column(name="year_of_establishment")
	private String establishYear;
	
	private String website;
	
	@Column(name="total_area_in_acre")
	private Double totalArea; 
	
	@Column(name="total_constructed_area_in_sqmt")
	private Double constructArea;
	
	@Column(name="location_id")
	private String areaType;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="institution_address_info",schema="institutionprofile",
	joinColumns = @JoinColumn(name="aishe_code",referencedColumnName = "aishe_code"),
	inverseJoinColumns = @JoinColumn(name="address_id",referencedColumnName = "id"))
	private List<Address> addresses;
	
	@OneToOne(cascade = CascadeType.ALL)
	private LocationInfo locationInfo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="institution_contact_info",schema="institutionprofile",
	joinColumns = @JoinColumn(name="aishe_code",referencedColumnName = "aishe_code"),
	inverseJoinColumns = @JoinColumn(name="person_details_id",referencedColumnName = "id"))
	private List<PersonDetails> contactInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	private NodalOfficer nodalOfficer;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<InstituteAffiliation> affiliations; 
	
	@ManyToMany
	@JoinTable(name="institution_statutory_body_details",schema="institutionprofile",
	joinColumns = @JoinColumn(name="aishe_code",referencedColumnName = "aishe_code"),
	inverseJoinColumns = @JoinColumn(name="college_institution_statutory_body_id",referencedColumnName = "id"))
	private List<StatutoryBody> statuaryBodies;
	
	@Column(name="institution_type_id")
	private Integer instituteTypeId;
	
	@Column(name="managed_by_id")
	private Integer managementId;
	
	@Column(name="is_specialized")
	private Boolean isSpecialized;
	
	@Column(name="speciality_id")
	private Integer specialityId;
	
	@Column(name="is_autonomous")
	private Boolean isAutonomous;
	
	
	@Column(name="is_evening")
	private Boolean isEvening;
	
	@Column(name="is_exclusive_for_girls")
	private Boolean isGirlExclusive;
	
	@Column(name="has_diploma_courses")
	private Boolean isOnlyDiploma;
	
	@Column(name="diploma_type_id")
	private Integer diplomaTypeId;
	
	@Column(name="is_staff_quarter_available")
	private Boolean isStaffQuarter;
	
	@OneToOne(cascade = CascadeType.ALL)
	private StaffQuarter staffQuarter;
	
	@Column(name="is_student_hostel_available")
	private Boolean isStudentHostel;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="institution_hostel_info",schema="institutionprofile",
	joinColumns = @JoinColumn(name="aishe_code",referencedColumnName = "aishe_code"),
	inverseJoinColumns = @JoinColumn(name="hostel_id",referencedColumnName = "id"))
	private List<StudentHostel> studentHostels;
	
	@Column(name="Offer_scholarship")
	private Boolean scholarship;
	
	@Column(name="Offers_fellowship")
	private Boolean fellowship;
	
	
	@Column(name="Offers_loan")
	private Boolean Offersloan;
	
	@Column(name="is_foreign_students_enrolled")
	private Boolean foreignStudent;
	
	@Column(name = "is_accredited")
	private Boolean accredated;
	
	
	public Integer getInstitution_id() {
		return institution_id;
	}
	public void setInstitution_id(Integer institution_id) {
		this.institution_id = institution_id;
	}
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEstablishYear() {
		return establishYear;
	}
	public void setEstablishYear(String establishYear) {
		this.establishYear = establishYear;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public Boolean getIsSpecialized() {
		return isSpecialized;
	}
	public void setIsSpecialized(Boolean isSpecialized) {
		this.isSpecialized = isSpecialized;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public LocationInfo getLocationInfo() {
		return locationInfo;
	}
	public void setLocationInfo(LocationInfo locationInfo) {
		this.locationInfo = locationInfo;
	}
	public NodalOfficer getNodalOfficer() {
		return nodalOfficer;
	}
	public void setNodalOfficer(NodalOfficer nodalOfficer) {
		this.nodalOfficer = nodalOfficer;
	}
	public List<InstituteAffiliation> getAffiliations() {
		return affiliations;
	}
	public void setAffiliations(List<InstituteAffiliation> affiliations) {
		this.affiliations = affiliations;
	}
	
	public Integer getManagementId() {
		return managementId;
	}
	public void setManagementId(Integer managementId) {
		this.managementId = managementId;
	}
	public Integer getSpecialityId() {
		return specialityId;
	}
	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}
	public Boolean getIsAutonomous() {
		return isAutonomous;
	}
	public void setIsAutonomous(Boolean isAutonomous) {
		this.isAutonomous = isAutonomous;
	}
	public Boolean getIsEvening() {
		return isEvening;
	}
	public void setIsEvening(Boolean isEvening) {
		this.isEvening = isEvening;
	}
	public Boolean getIsGirlExclusive() {
		return isGirlExclusive;
	}
	public void setIsGirlExclusive(Boolean isGirlExclusive) {
		this.isGirlExclusive = isGirlExclusive;
	}
	public Boolean getIsOnlyDiploma() {
		return isOnlyDiploma;
	}
	public void setIsOnlyDiploma(Boolean isOnlyDiploma) {
		this.isOnlyDiploma = isOnlyDiploma;
	}
	
	public Integer getDiplomaTypeId() {
		return diplomaTypeId;
	}
	public void setDiplomaTypeId(Integer diplomaTypeId) {
		this.diplomaTypeId = diplomaTypeId;
	}
	public Boolean getIsStaffQuarter() {
		return isStaffQuarter;
	}
	public void setIsStaffQuarter(Boolean isStaffQuarter) {
		this.isStaffQuarter = isStaffQuarter;
	}
	public StaffQuarter getStaffQuarter() {
		return staffQuarter;
	}
	public void setStaffQuarter(StaffQuarter staffQuarter) {
		this.staffQuarter = staffQuarter;
	}
	public Boolean getIsStudentHostel() {
		return isStudentHostel;
	}
	public void setIsStudentHostel(Boolean isStudentHostel) {
		this.isStudentHostel = isStudentHostel;
	}
	public List<StudentHostel> getStudentHostels() {
		return studentHostels;
	}
	public void setStudentHostels(List<StudentHostel> studentHostels) {
		this.studentHostels = studentHostels;
	}
	public Integer getInstituteTypeId() {
		return instituteTypeId;
	}
	public void setInstituteTypeId(Integer instituteTypeId) {
		this.instituteTypeId = instituteTypeId;
	}
	public Double getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(Double totalArea) {
		this.totalArea = totalArea;
	}
	public Double getConstructArea() {
		return constructArea;
	}
	public void setConstructArea(Double constructArea) {
		this.constructArea = constructArea;
	}
	
	public List<PersonDetails> getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(List<PersonDetails> contactInfo) {
		this.contactInfo = contactInfo;
	}
	public List<StatutoryBody> getStatuaryBodies() {
		return statuaryBodies;
	}
	public void setStatuaryBodies(List<StatutoryBody> statuaryBodies) {
		this.statuaryBodies = statuaryBodies;
	}
	public Boolean getScholarship() {
		return scholarship;
	}
	public void setScholarship(Boolean scholarship) {
		this.scholarship = scholarship;
	}
	public Boolean getFellowship() {
		return fellowship;
	}
	public void setFellowship(Boolean fellowship) {
		this.fellowship = fellowship;
	}
	public Boolean getOffersloan() {
		return Offersloan;
	}
	public void setOffersloan(Boolean offersloan) {
		Offersloan = offersloan;
	}
	public Boolean getForeignStudent() {
		return foreignStudent;
	}
	public void setForeignStudent(Boolean foreignStudent) {
		this.foreignStudent = foreignStudent;
	}
	public Boolean getAccredated() {
		return accredated;
	}
	public void setAccredated(Boolean accredated) {
		this.accredated = accredated;
	}
	
	
	
	
	
	 
	
	}
