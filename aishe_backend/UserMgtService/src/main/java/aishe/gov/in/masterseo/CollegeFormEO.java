package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "college_institution")
public class CollegeFormEO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6732141415304179701L;
	@EmbeddedId
	private StandAloneEmadedPK universityPk;
	@Column(name = "address_line1")
	private String addressLine1;
	@Column(name = "address_line2")
	private String addressLine2;
	@Column(name = "year_of_affiliation")
	private Integer yearAffiliation;
	@Column(name = "area")
	private Double area;
	@Column(name = "autonomous")
	private Boolean autonomous;
	@Column(name = "block_city_town")
	private String blockCityTown;
	@Column(name = "city")
	private String city;
	@Column(name = "constructed_area")
	private Double constructedArea;
	@Column(name = "year_of_establishment")
	private Integer yearEstablishment;
	@Column(name = "evening")
	private Boolean evening;
	@Column(name = "girl_exclusive")
	private Boolean girlExclusive;
	@Column(name = "has_diploma_courses")
	private Boolean diplomaCourses;
	@Column(name = "has_fellowships")
	private Boolean fellowships;
	@Column(name = "has_foreign_teachers")
	private Boolean foreignTeachers;
	@Column(name = "has_other_minority_data")
	private Boolean otherMinorityData;
	@Column(name = "student_hostel_available")
	private Boolean studentHostelAvailable;
	@Column(name = "staff_quarter_available")
	private Boolean staffQuarterAvailable;
	@Column(name = "is_foreign_students_enrolled")
	private Boolean fse;
	@Column(name = "latitude")
	private Double latitude;
	@Column(name = "location")
	private String location;
	@Column(name = "longitude")
	private Double longitude;
	@Column(name = "name")
	private String name;
	@Column(name = "no_of_student_hostel")
	private Integer nsh;
	@Column(name = "offers_loan")
	private Boolean offersLoan;
	@Column(name = "offers_scholarship")
	private Boolean offersScholarship;
	@Column(name = "other_affiliated_university_id")
	private String otherAffiliatedUniversity;
	@Column(name = "other_speciality")
	private String otherSpeciality;
	@Column(name = "other_statutory_body")
	private String otherStatutoryBody;
	@Column(name = "pin_code")
	private String pinCode;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "specialized")
	private Boolean specialized;
	@Column(name = "university_id")
	private String university;
	@Column(name = "website")
	private String website;
	@Column(name = "speciality_id")
	private String speciality;
	@Column(name = "diploma_course_id")
	private String diplomaCourse;
	@Column(name = "district_code")
	private String districtCode;
	@Column(name = "is_accredited")
	private Boolean isAccredited;
	@Column(name = "loan_id")
	private Integer loan;
	@Column(name = "financial_expenditure_id")
	private Integer financialExpenditure;
	@Column(name = "fellowships_id")
	private Integer fellowshipsId;
	@Column(name = "financial_income_id")
	private Integer financialIncome;
	@Column(name = "infrastructure_id")
	private Integer infrastructure;
	@Column(name = "management_id")
	private String management;
	@Column(name = "college_type_id")
	private String collegeType;
	//@Column(name = "nodalofficer_id")
	//private Integer nodalofficer;
	@Column(name = "scholarship_id")
	private Integer scholarship;
	@Column(name = "staff_quarter_id")
	private Integer staffQuarter;
	@Column(name = "state_code")
	private String stateCode;
	@Column(name = "statutory_body_id")
	private String statutoryBody;
	@Column(name = "approved_intake_capacity_intl_nri_pio_students")
	private Integer approved_intake_capacity_intl_nri_pio_students;
	@Column(name = "no_of_nss_male")
	private Integer noOfNssMale;
	@Column(name = "block_id")
	private Integer blockId;
	@Column(name = "ulb_id")
	private Integer ulbId;
	public StandAloneEmadedPK getUniversityPk() {
		return universityPk;
	}
	public void setUniversityPk(StandAloneEmadedPK universityPk) {
		this.universityPk = universityPk;
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
	public Integer getYearAffiliation() {
		return yearAffiliation;
	}
	public void setYearAffiliation(Integer yearAffiliation) {
		this.yearAffiliation = yearAffiliation;
	}
	
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public void setConstructedArea(Double constructedArea) {
		this.constructedArea = constructedArea;
	}
	public Boolean isAutonomous() {
		return autonomous;
	}
	public void setAutonomous(Boolean autonomous) {
		this.autonomous = autonomous;
	}
	public String getBlockCityTown() {
		return blockCityTown;
	}
	public void setBlockCityTown(String blockCityTown) {
		this.blockCityTown = blockCityTown;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public Double getConstructedArea() {
		return constructedArea;
	}
	public Integer getYearEstablishment() {
		return yearEstablishment;
	}
	public void setYearEstablishment(Integer yearEstablishment) {
		this.yearEstablishment = yearEstablishment;
	}
	public Boolean isEvening() {
		return evening;
	}
	public void setEvening(Boolean evening) {
		this.evening = evening;
	}
	public Boolean isGirlExclusive() {
		return girlExclusive;
	}
	public void setGirlExclusive(Boolean girlExclusive) {
		this.girlExclusive = girlExclusive;
	}
	public Boolean isDiplomaCourses() {
		return diplomaCourses;
	}
	public void setDiplomaCourses(Boolean diplomaCourses) {
		this.diplomaCourses = diplomaCourses;
	}
	public Boolean isFellowships() {
		return fellowships;
	}
	public void setFellowships(Boolean fellowships) {
		this.fellowships = fellowships;
	}
	public Boolean isForeignTeachers() {
		return foreignTeachers;
	}
	public void setForeignTeachers(Boolean foreignTeachers) {
		this.foreignTeachers = foreignTeachers;
	}
	public Boolean isOtherMinorityData() {
		return otherMinorityData;
	}
	public void setOtherMinorityData(Boolean otherMinorityData) {
		this.otherMinorityData = otherMinorityData;
	}
	public Boolean isStudentHostelAvailable() {
		return studentHostelAvailable;
	}
	public void setStudentHostelAvailable(Boolean studentHostelAvailable) {
		this.studentHostelAvailable = studentHostelAvailable;
	}
	public Boolean isStaffQuarterAvailable() {
		return staffQuarterAvailable;
	}
	public void setStaffQuarterAvailable(Boolean staffQuarterAvailable) {
		this.staffQuarterAvailable = staffQuarterAvailable;
	}
	public Boolean isFse() {
		return fse;
	}
	public void setFse(Boolean fse) {
		this.fse = fse;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNsh() {
		return nsh;
	}
	public void setNsh(Integer nsh) {
		this.nsh = nsh;
	}
	public Boolean isOffersLoan() {
		return offersLoan;
	}
	public void setOffersLoan(Boolean offersLoan) {
		this.offersLoan = offersLoan;
	}
	public Boolean isOffersScholarship() {
		return offersScholarship;
	}
	public void setOffersScholarship(Boolean offersScholarship) {
		this.offersScholarship = offersScholarship;
	}
	public String getOtherAffiliatedUniversity() {
		return otherAffiliatedUniversity;
	}
	public void setOtherAffiliatedUniversity(String otherAffiliatedUniversity) {
		this.otherAffiliatedUniversity = otherAffiliatedUniversity;
	}
	public String getOtherSpeciality() {
		return otherSpeciality;
	}
	public void setOtherSpeciality(String otherSpeciality) {
		this.otherSpeciality = otherSpeciality;
	}
	public String getOtherStatutoryBody() {
		return otherStatutoryBody;
	}
	public void setOtherStatutoryBody(String otherStatutoryBody) {
		this.otherStatutoryBody = otherStatutoryBody;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Boolean isSpecialized() {
		return specialized;
	}
	public void setSpecialized(Boolean specialized) {
		this.specialized = specialized;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getDiplomaCourse() {
		return diplomaCourse;
	}
	public void setDiplomaCourse(String diplomaCourse) {
		this.diplomaCourse = diplomaCourse;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public Integer getLoan() {
		return loan;
	}
	public void setLoan(Integer loan) {
		this.loan = loan;
	}
	public Integer getFinancialExpenditure() {
		return financialExpenditure;
	}
	public void setFinancialExpenditure(Integer financialExpenditure) {
		this.financialExpenditure = financialExpenditure;
	}
	public Integer getFellowshipsId() {
		return fellowshipsId;
	}
	public void setFellowshipsId(Integer fellowshipsId) {
		this.fellowshipsId = fellowshipsId;
	}
	public Integer getFinancialIncome() {
		return financialIncome;
	}
	public void setFinancialIncome(Integer financialIncome) {
		this.financialIncome = financialIncome;
	}
	public Integer getInfrastructure() {
		return infrastructure;
	}
	public void setInfrastructure(Integer infrastructure) {
		this.infrastructure = infrastructure;
	}
	public String getManagement() {
		return management;
	}
	public void setManagement(String management) {
		this.management = management;
	}
	public String getCollegeType() {
		return collegeType;
	}
	public void setCollegeType(String collegeType) {
		this.collegeType = collegeType;
	}
	public Integer getScholarship() {
		return scholarship;
	}
	public void setScholarship(Integer scholarship) {
		this.scholarship = scholarship;
	}
	public Integer getStaffQuarter() {
		return staffQuarter;
	}
	public void setStaffQuarter(Integer staffQuarter) {
		this.staffQuarter = staffQuarter;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStatutoryBody() {
		return statutoryBody;
	}
	public void setStatutoryBody(String statutoryBody) {
		this.statutoryBody = statutoryBody;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Boolean isAccredited() {
		return isAccredited;
	}
	public void setAccredited(Boolean isAccredited) {
		this.isAccredited = isAccredited;
	}
	public Integer getApproved_intake_capacity_intl_nri_pio_students() {
		return approved_intake_capacity_intl_nri_pio_students;
	}
	public void setApproved_intake_capacity_intl_nri_pio_students(Integer approved_intake_capacity_intl_nri_pio_students) {
		this.approved_intake_capacity_intl_nri_pio_students = approved_intake_capacity_intl_nri_pio_students;
	}
	public Boolean getIsAccredited() {
		return isAccredited;
	}
	public void setIsAccredited(Boolean isAccredited) {
		this.isAccredited = isAccredited;
	}
	public Integer getNoOfNssMale() {
		return noOfNssMale;
	}
	public void setNoOfNssMale(Integer noOfNssMale) {
		this.noOfNssMale = noOfNssMale;
	}
	public Boolean getAutonomous() {
		return autonomous;
	}
	public Boolean getEvening() {
		return evening;
	}
	public Boolean getGirlExclusive() {
		return girlExclusive;
	}
	public Boolean getDiplomaCourses() {
		return diplomaCourses;
	}
	public Boolean getFellowships() {
		return fellowships;
	}
	public Boolean getForeignTeachers() {
		return foreignTeachers;
	}
	public Boolean getOtherMinorityData() {
		return otherMinorityData;
	}
	public Boolean getStudentHostelAvailable() {
		return studentHostelAvailable;
	}
	public Boolean getStaffQuarterAvailable() {
		return staffQuarterAvailable;
	}
	public Boolean getFse() {
		return fse;
	}
	public Boolean getOffersLoan() {
		return offersLoan;
	}
	public Boolean getOffersScholarship() {
		return offersScholarship;
	}
	public Boolean getSpecialized() {
		return specialized;
	}
	public Integer getBlockId() {
		return blockId;
	}
	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}
	public Integer getUlbId() {
		return ulbId;
	}
	public void setUlbId(Integer ulbId) {
		this.ulbId = ulbId;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}
	
	}
