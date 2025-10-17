package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.university")
public class UniversityFormEO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private UniversityId universityID;
	@EmbeddedId
	private UniversityEmadedPK universityPk;
	@Column(name = "name")
	private String name;
	@Column(name = "address_line1")
	private String addressLine1;
	@Column(name = "address_line2")
	private String addressLine2;
	@Column(name = "city")
	private String city;
	@Column(name = "location")
	private String location;
	@Column(name = "other_speciality")
	private String otherSpeciality;
	@Column(name = "website")
	private String website;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "pin_code")
	private String pinCode;
	@Column(name = "block_city_town")
	private String blockCityTown;
	@Column(name = "area")
	private Double area;
	@Column(name = "constructed_area")
	private Double constructedArea;
	@Column(name = "latitude")
	private Double latitude;
	@Column(name = "longitude")
	private Double longitude;
	@Column(name = "no_of_student_hostel")
	private Integer noOfHostel;
	@Column(name = "no_of_regional_centers")
	private Integer noOfRegionalCenters;
	@Column(name = "year_of_establishment")
	private Integer establishmentYear;
	@Column(name = "year_when_declared_university")
	private Integer yearWhenDeclaredUniversity;
	@Column(name = "no_of_off_shore_center")
	private Integer noOfOffShoreCenter;
	@Column(name = "specialized")
	private Boolean specialized;
	@Column(name = "has_other_minority_data")
	private Boolean hasOtherMinorityData;
	@Column(name = "girl_exclusive")
	private Boolean girl_exclusive;
	@Column(name = "staff_quarter_available")
	private Boolean haveStaffQuarter;
	@Column(name = "student_hostel_available")
	private Boolean haveHostel;
	@Column(name = "offers_distance_programme")
	private Boolean offersDistanceProgramme;
	@Column(name = "has_faculty_regular_courses")
	private Boolean hasFacultyRegularCourses;
	@Column(name = "has_department_regular_courses")
	private Boolean hasDepartmentRegularCourses;
	@Column(name = "has_other_regular_courses")
	private Boolean hasOtherRegularCourses;
	@Column(name = "constituted_from_colleges")
	private Boolean constitutedFromColleges;
	@Column(name = "offers_scholarship")
	private Boolean offersScholarship;
	@Column(name = "offers_loan")
	private Boolean offersLoan;
	@Column(name = "is_accredited")
	private Boolean isAccredited;
	@Column(name = "is_foreign_students_enrolled")
	private Boolean isForeignStudentsEnrolled;
	@Column(name = "off_shore_center_available")
	private Boolean offShoreCenterAvailable;
	@Column(name = "has_fellowships")
	private Boolean hasFellowships;
	@Column(name = "has_foreign_teachers")
	private Boolean hasForeignTeachers;
	@Column(name = "is_university_uploaded_act_statues")
	private Boolean isUniversityUploadedActStatues;
	@Column(name = "is_university_complying_rules")
	private Boolean isUniversityComplyingRules;
	@Column(name = "is_university180_actual_teaching_days")
	private Boolean isUniversity180ActualTeachingDays;
	@Column(name = "infrastructure_id")
	private Integer infrastructureId;
	@Column(name = "loan_id")
	private Integer loanId;
	@Column(name = "scholarship_id")
	private Integer scholarshipId;
	@Column(name = "fellowships_id")
	private Integer felloshipsId;
	@Column(name = "financial_income_id")
	private Integer financialIncomeId;
	@Column(name = "financial_expenditure_id")
	private Integer financialExpenditureId;
	@Column(name = "nodal_officer_id")
	private Integer nodalOfficer;
	@Column(name = "staff_quarter_id")
	private Integer staffQuarter;
	@Column(name = "district_code")
	private String districtCode;
	@Column(name = "state_code")
	private String stateCode;
	@Column(name = "type_id")
	private String instituteType;
	@Column(name = "speciality_id")
	private String speciality;

	
	public UniversityEmadedPK getUniversityPk() {
		return universityPk;
	}

	public void setUniversityPk(UniversityEmadedPK universityPk) {
		this.universityPk = universityPk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOtherSpeciality() {
		return otherSpeciality;
	}

	public void setOtherSpeciality(String otherSpeciality) {
		this.otherSpeciality = otherSpeciality;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getBlockCityTown() {
		return blockCityTown;
	}

	public void setBlockCityTown(String blockCityTown) {
		this.blockCityTown = blockCityTown;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getConstructedArea() {
		return constructedArea;
	}

	public void setConstructedArea(Double constructedArea) {
		this.constructedArea = constructedArea;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getNoOfHostel() {
		return noOfHostel;
	}

	public void setNoOfHostel(Integer noOfHostel) {
		this.noOfHostel = noOfHostel;
	}

	public Integer getNoOfRegionalCenters() {
		return noOfRegionalCenters;
	}

	public void setNoOfRegionalCenters(Integer noOfRegionalCenters) {
		this.noOfRegionalCenters = noOfRegionalCenters;
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

	public Integer getNoOfOffShoreCenter() {
		return noOfOffShoreCenter;
	}

	public void setNoOfOffShoreCenter(Integer noOfOffShoreCenter) {
		this.noOfOffShoreCenter = noOfOffShoreCenter;
	}

	public Boolean getSpecialized() {
		return specialized;
	}

	public void setSpecialized(Boolean specialized) {
		this.specialized = specialized;
	}

	public Boolean getHasOtherMinorityData() {
		return hasOtherMinorityData;
	}

	public void setHasOtherMinorityData(Boolean hasOtherMinorityData) {
		this.hasOtherMinorityData = hasOtherMinorityData;
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

	public Boolean getOffersDistanceProgramme() {
		return offersDistanceProgramme;
	}

	public void setOffersDistanceProgramme(Boolean offersDistanceProgramme) {
		this.offersDistanceProgramme = offersDistanceProgramme;
	}

	public Boolean getHasFacultyRegularCourses() {
		return hasFacultyRegularCourses;
	}

	public void setHasFacultyRegularCourses(Boolean hasFacultyRegularCourses) {
		this.hasFacultyRegularCourses = hasFacultyRegularCourses;
	}

	public Boolean getHasDepartmentRegularCourses() {
		return hasDepartmentRegularCourses;
	}

	public void setHasDepartmentRegularCourses(Boolean hasDepartmentRegularCourses) {
		this.hasDepartmentRegularCourses = hasDepartmentRegularCourses;
	}

	public Boolean getHasOtherRegularCourses() {
		return hasOtherRegularCourses;
	}

	public void setHasOtherRegularCourses(Boolean hasOtherRegularCourses) {
		this.hasOtherRegularCourses = hasOtherRegularCourses;
	}

	public Boolean getConstitutedFromColleges() {
		return constitutedFromColleges;
	}

	public void setConstitutedFromColleges(Boolean constitutedFromColleges) {
		this.constitutedFromColleges = constitutedFromColleges;
	}

	public Boolean getOffersScholarship() {
		return offersScholarship;
	}

	public void setOffersScholarship(Boolean offersScholarship) {
		this.offersScholarship = offersScholarship;
	}

	public Boolean getOffersLoan() {
		return offersLoan;
	}

	public void setOffersLoan(Boolean offersLoan) {
		this.offersLoan = offersLoan;
	}

	public Boolean getIsAccredited() {
		return isAccredited;
	}

	public void setIsAccredited(Boolean isAccredited) {
		this.isAccredited = isAccredited;
	}

	public Boolean getIsForeignStudentsEnrolled() {
		return isForeignStudentsEnrolled;
	}

	public void setIsForeignStudentsEnrolled(Boolean isForeignStudentsEnrolled) {
		this.isForeignStudentsEnrolled = isForeignStudentsEnrolled;
	}

	public Boolean getOffShoreCenterAvailable() {
		return offShoreCenterAvailable;
	}

	public void setOffShoreCenterAvailable(Boolean offShoreCenterAvailable) {
		this.offShoreCenterAvailable = offShoreCenterAvailable;
	}

	public Boolean getHasFellowships() {
		return hasFellowships;
	}

	public void setHasFellowships(Boolean hasFellowships) {
		this.hasFellowships = hasFellowships;
	}

	public Boolean getHasForeignTeachers() {
		return hasForeignTeachers;
	}

	public void setHasForeignTeachers(Boolean hasForeignTeachers) {
		this.hasForeignTeachers = hasForeignTeachers;
	}

	public Boolean getIsUniversityUploadedActStatues() {
		return isUniversityUploadedActStatues;
	}

	public void setIsUniversityUploadedActStatues(Boolean isUniversityUploadedActStatues) {
		this.isUniversityUploadedActStatues = isUniversityUploadedActStatues;
	}

	public Boolean getIsUniversityComplyingRules() {
		return isUniversityComplyingRules;
	}

	public void setIsUniversityComplyingRules(Boolean isUniversityComplyingRules) {
		this.isUniversityComplyingRules = isUniversityComplyingRules;
	}

	public Boolean getIsUniversity180ActualTeachingDays() {
		return isUniversity180ActualTeachingDays;
	}

	public void setIsUniversity180ActualTeachingDays(Boolean isUniversity180ActualTeachingDays) {
		this.isUniversity180ActualTeachingDays = isUniversity180ActualTeachingDays;
	}

	public Integer getInfrastructureId() {
		return infrastructureId;
	}

	public void setInfrastructureId(Integer infrastructureId) {
		this.infrastructureId = infrastructureId;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getScholarshipId() {
		return scholarshipId;
	}

	public void setScholarshipId(Integer scholarshipId) {
		this.scholarshipId = scholarshipId;
	}

	public Integer getFelloshipsId() {
		return felloshipsId;
	}

	public void setFelloshipsId(Integer felloshipsId) {
		this.felloshipsId = felloshipsId;
	}

	public Integer getFinancialIncomeId() {
		return financialIncomeId;
	}

	public void setFinancialIncomeId(Integer financialIncomeId) {
		this.financialIncomeId = financialIncomeId;
	}

	public Integer getFinancialExpenditureId() {
		return financialExpenditureId;
	}

	public void setFinancialExpenditureId(Integer financialExpenditureId) {
		this.financialExpenditureId = financialExpenditureId;
	}

	public Integer getNodalOfficer() {
		return nodalOfficer;
	}

	public void setNodalOfficer(Integer nodalOfficer) {
		this.nodalOfficer = nodalOfficer;
	}

	public Integer getStaffQuarter() {
		return staffQuarter;
	}

	public void setStaffQuarter(Integer staffQuarter) {
		this.staffQuarter = staffQuarter;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getInstituteType() {
		return instituteType;
	}

	public void setInstituteType(String instituteType) {
		this.instituteType = instituteType;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
}