package com.nic.aishe.master.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.nic.aishe.master.util.CollegeId;

@Entity
@Table(name = "college_institution")
@IdClass(CollegeId.class)
public class CollegeInfo implements Serializable {

	private static final long serialVersionUID = 8690843058969832724L;

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Transient
	private Integer aisheCode;

	@Column(name = "address_line1")
	private String addressline1;

	@Column(name = "address_line2")
	private String addressline2;

	@Column(name = "area")
	private Double area;

	@Column(name = "city")
	private String city;

	@Column(name = "website")
	private String website;

	@OneToOne
	@JoinColumn(name = "state_code")
	private State stateCode;

	@OneToOne
	@JoinColumn(name = "district_code")
	private District districtCode;

	@Column(name = "name")
	private String name;

	@Column(name = "year_of_establishment")
	private Integer establishmentYear;

	@Column(name = "constructed_area")
	private Double constructedArea;

//	@Column(name = "college_type_id")
//	private String typeId;

	@OneToOne
	
	@JoinColumn(name = "college_type_id", referencedColumnName = "id")
	private RefUniversityCollegeType instituteType;

	@Column(name = "location")
	private String location;

	@Column(name = "girl_exclusive")
	private Boolean girl_exclusive;

	@Column(name = "autonomous")
	private Boolean autonomous;

	@Id
	@Column(name = "survey_year")
	private Integer surveyYear;

	@Column(name = "university_id")
	private String universityId;

//	@OneToOne
	// 
//	@JoinColumn(name="university_id",referencedColumnName = "id")
//	private University university;

//	@Column(name = "nodalofficer_id")
//	private Integer nodalOfficer;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)	
	@JoinColumn(name = "nodalofficer_id", referencedColumnName = "id")
	private NodalOfficer nodalOfficer;

//	@Column(name = "statutory_body_id")
//	private String statutoryBody;

	@OneToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "statutory_body_id", referencedColumnName = "id")
	private StatutoryBody statutoryBody;

	@Column(name = "other_statutory_body")
	private String otherStatutoryBody;

	@Column(name = "year_of_affiliation")
	private Integer affiliationYear;

//	@Column(name = "management_id")
//	private String management;

	@OneToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "management_id", referencedColumnName = "id")
	private InstituteManagement instituteManagement;

	@Column(name = "specialized")
	private Boolean specialized;

	@Column(name = "other_speciality")
	private String otherSpeciality;

	@Column(name = "evening")
	private Boolean evening;

	@Column(name = "staff_quarter_available")
	private Boolean haveStaffQuarter;

//	@Column(name = "staff_quarter_id")
//	private Integer staffQuarter;

	@OneToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "staff_quarter_id", referencedColumnName = "id")
	private StaffQuarter staffQuarter;

	@Column(name = "student_hostel_available")
	private Boolean haveHostel;

	@Column(name = "no_of_student_hostel")
	private Integer noOfHostel;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)	
	@JoinTable(name = "college_institution_student_hostel", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") }, inverseJoinColumns = @JoinColumn(name = "student_hostel_id", referencedColumnName = "id"))
	private List<StudentHostel> hostels;

	@Column(name = "speciality_id")
	private String speciality;

//	@OneToOne(fetch = FetchType.LAZY)	
//	@JoinColumn(name = "speciality_id", referencedColumnName = "id")
//	private Speciality Speciality;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)	
	@JoinColumn(name = "financial_income_id", referencedColumnName = "id")
	private FinanceIncome financeIncome;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)	
	@JoinColumn(name = "financial_expenditure_id", referencedColumnName = "id")
	private Expenditure expenditure;

//	@Column(name = "infrastructure_id")
//	private Integer infrastructureId;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)	
	@JoinColumn(name = "infrastructure_id", referencedColumnName = "id")
	private Infrastructure infrastructure;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

//	@Column(name = "scholarship_id")
//	private Integer scholarshipId;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)	
	@JoinColumn(name = "scholarship_id", referencedColumnName = "id")
	private ScholarshipInfo scholarshipInfo;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)	
	@JoinColumn(name = "fellowships_id", referencedColumnName = "id")
	private FellowshipInfo fellowshipInfo;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)	
	@JoinColumn(name = "loan_id", referencedColumnName = "id")
	private EducationLoan educationLoan;

//	@OneToOne
//	
//	@JoinColumn(name="scholarship_id",referencedColumnName = "id")
//	private Scholarship scholarship;

//	@Column(name = "loan_id")
//	private Integer loanId;

	@Column(name = "is_accredited")
	private Boolean accredited;

	@Column(name = "is_foreign_students_enrolled")
	private Boolean isForeignStudents;

	@Column(name = "offers_scholarship")
	private Boolean offersScholarship;

	@Column(name = "offers_loan")
	private Boolean offersLoan;

	@Column(name = "has_diploma_courses")
	private Boolean hasDiplomaCourses;

//	@Column(name = "diploma_course_id")
//	private Integer diplomaCourseId;

	@OneToOne(fetch = FetchType.LAZY)
	
	@JoinColumn(name = "diploma_course_id", referencedColumnName = "id")
	private DiplomaCourse diplomaCourse;

	@Column(name = "pin_code")
	private String pinCode;

	@Column(name = "has_fellowships")
	private Boolean hasFellowships;

//	@Column(name = "fellowships_id")
//	private Integer fellowshipsId;

	@Column(name = "other_affiliated_university_id")
	private String otherAffiliatedUniversityId;

//	@OneToOne
//	
//	@JoinColumn(name="other_affiliated_university_id",referencedColumnName = "id")
//	private University otherUniversity;

	@Column(name = "has_other_minority_data")
	private Boolean hasOtherMinority;

	@Column(name = "block_city_town")
	private String blockCityTown;

	@Column(name = "has_foreign_teachers")
	private Boolean hasForeignTeachers;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "college_institution_non_teaching_staff_count", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") }, inverseJoinColumns = @JoinColumn(name = "non_teaching_staff_count_id"))
	private List<StaffCount> staffCounts;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "college_institution_course_enrolled_student_count", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") },
			inverseJoinColumns = @JoinColumn(name = "course_enrolled_student_count_id", referencedColumnName = "id"))
	private List<CourseEnrolledStudentCount> courseEnrolledStudentCounts;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "college_institution_enrolled_regular_student", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") }, inverseJoinColumns = @JoinColumn(name = "enrolled_regular_student_id", referencedColumnName = "id"))
	private List<RegularStudentEnrollment> regularStudentEnrollments;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "college_institution_enrolled_distance_student", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") }, inverseJoinColumns = @JoinColumn(name = "enrolled_distance_student_institute_id", referencedColumnName = "id"))
	private List<DistanceStudentEnrollment> distanceStudentEnrollments;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "college_institution_course_enrolled_foreign_student_count", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") }, inverseJoinColumns = @JoinColumn(name = "course_enrolled_foreign_student_count_id", referencedColumnName = "id"))
	private List<CourseEnrolledForeignStudentCount> courseEnrolledForeignStudentCounts;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "college_institution_enrolled_foreign_student_count", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") }, inverseJoinColumns = @JoinColumn(name = "enrolled_foreign_student_count_id", referencedColumnName = "id"))
	private List<ForeignStudentEnrollment> foreignStudentEnrollments;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "college_institution_course_examination_result", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") }, inverseJoinColumns = @JoinColumn(name = "course_examination_result_id", referencedColumnName = "id"))
	private List<CourseExamResult> courseExamResults;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "college_institution_examination_result", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") }, inverseJoinColumns = @JoinColumn(name = "examination_result_id", referencedColumnName = "id"))
	private List<ExamResult> examResults;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "college_institution_accreditation", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") },
			inverseJoinColumns = @JoinColumn(name = "accreditation_id", referencedColumnName = "id"))
	private List<Accreditation> accreditations;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinTable(name = "college_institution_teaching_staff", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") }, 
	inverseJoinColumns = @JoinColumn(name = "teaching_staff_id", referencedColumnName = "id"))
	private List<TeachingStaff> teachingStaffs;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "college_institution_teaching_staff_sanctioned_strength", joinColumns = {
			@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
			@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") }, 
	inverseJoinColumns = @JoinColumn(name = "teaching_staff_sanctioned_strength_id", referencedColumnName = "id"))
	private List<TeachingSactionStrength> teachingSactionStrengths;
	@ManyToOne
	@JoinColumn(name = "placement_details_id")
	private PlacementDetails placementDetails;
	
	@Column(name = "status_prior_to_establishment")
	private String statusPriorToEstablishment;
	@Column(name = "ownership_status")
	private Integer ownershipStatus;
	@Column(name = "whether_village_adopted_under_unnat_bharat")
	private Boolean whetherVillageAdoptedUnderUnnatBharat;
	@Column(name = "boys_exclusive")
	private Boolean boysExclusive;
	@Column(name = "approved_intake_capacity_intl_nri_pio_students")
	private Integer approvedIntakeCapacityIntlNriPioStudents;
	@Column(name = "whether_awarded_by_university")
	private Boolean whetherAwardedByUniversity;
	@Column(name = "awarded_by_university_id")
	private String awardedByUniversityId;
	@Column(name = "other_diploma")
	private String otherDiploma;
	
	@Column(name = "is_minority_managed_institution")
	private Boolean isMinorityManagedInstitution=false;
	@Column(name = "minority_community_type")
	private Integer typeMinorityCommunityManagingId;
	@Column(name = "has_ncc_unit")
	private Boolean isNCC=false;
	@Column(name = "no_of_ncc_female")
	private Integer femaleEnrolledNCC;
	@Column(name = "no_of_ncc_total")
	private Integer totalEnrolledNCC;
	@Column(name = "has_nss")
	private Boolean isNSS=false;
	@Column(name = "no_of_nss_female")
	private Integer femaleEnrolledNSS;
	@Column(name = "no_of_nss_total")
	private Integer totalEnrolledNSS;
	@Column(name = "no_of_student_enrolled_in_ncc")
	private Integer studentEnrolledNCC;
	@Column(name = "no_of_student_enrolled_in_nss")
	private Integer studentEnrolledNSS;
	
	public String getStatusPriorToEstablishment() {
		return statusPriorToEstablishment;
	}

	public void setStatusPriorToEstablishment(String statusPriorToEstablishment) {
		this.statusPriorToEstablishment = statusPriorToEstablishment;
	}

	public Integer getOwnershipStatus() {
		return ownershipStatus;
	}

	public void setOwnershipStatus(Integer ownershipStatus) {
		this.ownershipStatus = ownershipStatus;
	}

	public Boolean getWhetherVillageAdoptedUnderUnnatBharat() {
		return whetherVillageAdoptedUnderUnnatBharat;
	}

	public void setWhetherVillageAdoptedUnderUnnatBharat(Boolean whetherVillageAdoptedUnderUnnatBharat) {
		this.whetherVillageAdoptedUnderUnnatBharat = whetherVillageAdoptedUnderUnnatBharat;
	}

	public Boolean getBoysExclusive() {
		return boysExclusive;
	}

	public void setBoysExclusive(Boolean boysExclusive) {
		this.boysExclusive = boysExclusive;
	}

	public Integer getApprovedIntakeCapacityIntlNriPioStudents() {
		return approvedIntakeCapacityIntlNriPioStudents;
	}

	public void setApprovedIntakeCapacityIntlNriPioStudents(Integer approvedIntakeCapacityIntlNriPioStudents) {
		this.approvedIntakeCapacityIntlNriPioStudents = approvedIntakeCapacityIntlNriPioStudents;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public State getStateCode() {
		return stateCode;
	}

	public void setStateCode(State stateCode) {
		this.stateCode = stateCode;
	}

	public District getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(District districtCode) {
		this.districtCode = districtCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEstablishmentYear() {
		return establishmentYear;
	}

	public void setEstablishmentYear(Integer establishmentYear) {
		this.establishmentYear = establishmentYear;
	}

	public Double getConstructedArea() {
		return constructedArea;
	}

	public void setConstructedArea(Double constructedArea) {
		this.constructedArea = constructedArea;
	}

	public RefUniversityCollegeType getInstituteType() {
		return instituteType;
	}

	public void setInstituteType(RefUniversityCollegeType instituteType) {
		this.instituteType = instituteType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getGirl_exclusive() {
		return girl_exclusive;
	}

	public void setGirl_exclusive(Boolean girl_exclusive) {
		this.girl_exclusive = girl_exclusive;
	}

	public Boolean getAutonomous() {
		return autonomous;
	}

	public void setAutonomous(Boolean autonomous) {
		this.autonomous = autonomous;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

//	public University getUniversity() {
//		return university;
//	}
//
//	public void setUniversity(University university) {
//		this.university = university;
//	}

	public NodalOfficer getNodalOfficer() {
		return nodalOfficer;
	}

	public void setNodalOfficer(NodalOfficer nodalOfficer) {
		this.nodalOfficer = nodalOfficer;
	}

	public StatutoryBody getStatutoryBody() {
		return statutoryBody;
	}

	public void setStatutoryBody(StatutoryBody statutoryBody) {
		this.statutoryBody = statutoryBody;
	}

	public String getOtherStatutoryBody() {
		return otherStatutoryBody;
	}

	public void setOtherStatutoryBody(String otherStatutoryBody) {
		this.otherStatutoryBody = otherStatutoryBody;
	}

	public Integer getAffiliationYear() {
		return affiliationYear;
	}

	public void setAffiliationYear(Integer affiliationYear) {
		this.affiliationYear = affiliationYear;
	}

	public InstituteManagement getInstituteManagement() {
		return instituteManagement;
	}

	public void setInstituteManagement(InstituteManagement instituteManagement) {
		this.instituteManagement = instituteManagement;
	}

	public Boolean getSpecialized() {
		return specialized;
	}

	public void setSpecialized(Boolean specialized) {
		this.specialized = specialized;
	}

	public String getOtherSpeciality() {
		return otherSpeciality;
	}

	public void setOtherSpeciality(String otherSpeciality) {
		this.otherSpeciality = otherSpeciality;
	}

	public Boolean getEvening() {
		return evening;
	}

	public void setEvening(Boolean evening) {
		this.evening = evening;
	}

	public Boolean getHaveStaffQuarter() {
		return haveStaffQuarter;
	}

	public void setHaveStaffQuarter(Boolean haveStaffQuarter) {
		this.haveStaffQuarter = haveStaffQuarter;
	}

	public StaffQuarter getStaffQuarter() {
		return staffQuarter;
	}

	public void setStaffQuarter(StaffQuarter staffQuarter) {
		this.staffQuarter = staffQuarter;
	}

	public Boolean getHaveHostel() {
		return haveHostel;
	}

	public void setHaveHostel(Boolean haveHostel) {
		this.haveHostel = haveHostel;
	}

	public Integer getNoOfHostel() {
		return noOfHostel;
	}

	public void setNoOfHostel(Integer noOfHostel) {
		this.noOfHostel = noOfHostel;
	}

	public List<StudentHostel> getHostels() {
		return hostels;
	}

	public void setHostels(List<StudentHostel> hostels) {
		this.hostels = hostels;
	}

	public FinanceIncome findFinanceIncome() {
		return financeIncome;
	}

	public void addFinanceIncome(FinanceIncome financeIncome) {
		this.financeIncome = financeIncome;
	}

	public Expenditure findExpenditure() {
		return expenditure;
	}

	public void addExpenditure(Expenditure expenditure) {
		this.expenditure = expenditure;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Boolean getAccredited() {
		return accredited;
	}

	public void setAccredited(Boolean accredited) {
		this.accredited = accredited;
	}

	public Boolean getIsForeignStudents() {
		return isForeignStudents;
	}

	public void setIsForeignStudents(Boolean isForeignStudents) {
		this.isForeignStudents = isForeignStudents;
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

	public Boolean getHasDiplomaCourses() {
		return hasDiplomaCourses;
	}

	public void setHasDiplomaCourses(Boolean hasDiplomaCourses) {
		this.hasDiplomaCourses = hasDiplomaCourses;
	}

	public DiplomaCourse getDiplomaCourse() {
		return diplomaCourse;
	}

	public void setDiplomaCourse(DiplomaCourse diplomaCourse) {
		this.diplomaCourse = diplomaCourse;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Boolean getHasFellowships() {
		return hasFellowships;
	}

	public void setHasFellowships(Boolean hasFellowships) {
		this.hasFellowships = hasFellowships;
	}

//	public University getOtherUniversity() {
//		return otherUniversity;
//	}
//
//	public void setOtherUniversity(University otherUniversity) {
//		this.otherUniversity = otherUniversity;
//	}

	public Boolean getHasOtherMinority() {
		return hasOtherMinority;
	}

	public void setHasOtherMinority(Boolean hasOtherMinority) {
		this.hasOtherMinority = hasOtherMinority;
	}

	public String getBlockCityTown() {
		return blockCityTown;
	}

	public void setBlockCityTown(String blockCityTown) {
		this.blockCityTown = blockCityTown;
	}

	public Boolean getHasForeignTeachers() {
		return hasForeignTeachers;
	}

	public void setHasForeignTeachers(Boolean hasForeignTeachers) {
		this.hasForeignTeachers = hasForeignTeachers;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public String getOtherAffiliatedUniversityId() {
		return otherAffiliatedUniversityId;
	}

	public void setOtherAffiliatedUniversityId(String otherAffiliatedUniversityId) {
		this.otherAffiliatedUniversityId = otherAffiliatedUniversityId;
	}

	public List<StaffCount> listStaffCounts() {
		return staffCounts;
	}

	public void addStaffCounts(List<StaffCount> staffCounts) {
		this.staffCounts = staffCounts;
	}

	public List<CourseEnrolledStudentCount> listCourseEnrolledStudentCounts() {
		return courseEnrolledStudentCounts;
	}

	public void addCourseEnrolledStudentCounts(List<CourseEnrolledStudentCount> courseEnrolledStudentCounts) {
		this.courseEnrolledStudentCounts = courseEnrolledStudentCounts;
	}

	public List<RegularStudentEnrollment> listRegularStudentEnrollments() {
		return regularStudentEnrollments;
	}

	public void addRegularStudentEnrollments(List<RegularStudentEnrollment> regularStudentEnrollments) {
		this.regularStudentEnrollments = regularStudentEnrollments;
	}

	public List<DistanceStudentEnrollment> listDistanceStudentEnrollments() {
		return distanceStudentEnrollments;
	}

	public void addDistanceStudentEnrollments(List<DistanceStudentEnrollment> distanceStudentEnrollments) {
		this.distanceStudentEnrollments = distanceStudentEnrollments;
	}

	public List<CourseEnrolledForeignStudentCount> listCourseEnrolledForeignStudentCounts() {
		return courseEnrolledForeignStudentCounts;
	}

	public void addCourseEnrolledForeignStudentCounts(
			List<CourseEnrolledForeignStudentCount> courseEnrolledForeignStudentCounts) {
		this.courseEnrolledForeignStudentCounts = courseEnrolledForeignStudentCounts;
	}

	public List<ForeignStudentEnrollment> listForeignStudentEnrollments() {
		return foreignStudentEnrollments;
	}

	public void addForeignStudentEnrollments(List<ForeignStudentEnrollment> foreignStudentEnrollments) {
		this.foreignStudentEnrollments = foreignStudentEnrollments;
	}

	public List<CourseExamResult> listCourseExamResults() {
		return courseExamResults;
	}
	

	public PlacementDetails getPlacementDetails() {
		return placementDetails;
	}

	public void setPlacementDetails(PlacementDetails placementDetails) {
		this.placementDetails = placementDetails;
	}

	public void addCourseExamResults(List<CourseExamResult> courseExamResults) {
		this.courseExamResults = courseExamResults;
	}

	public List<ExamResult> listExamResults() {
		return examResults;
	}

	public void addExamResults(List<ExamResult> examResults) {
		this.examResults = examResults;
	}

	public Infrastructure findInfrastructure() {
		return infrastructure;
	}

	public void addInfrastructure(Infrastructure infrastructure) {
		this.infrastructure = infrastructure;
	}

	public ScholarshipInfo findScholarshipInfo() {
		return scholarshipInfo;
	}

	public void addScholarshipInfo(ScholarshipInfo scholarshipInfo) {
		this.scholarshipInfo = scholarshipInfo;
	}

	public FellowshipInfo findFellowshipInfo() {
		return fellowshipInfo;
	}

	public void addFellowshipInfo(FellowshipInfo fellowshipInfo) {
		this.fellowshipInfo = fellowshipInfo;
	}

	public EducationLoan findEducationLoan() {
		return educationLoan;
	}

	public void addEducationLoan(EducationLoan educationLoan) {
		this.educationLoan = educationLoan;
	}

	public List<Accreditation> listAccreditations() {
		return accreditations;
	}


	public void addAccreditations(List<Accreditation> accreditations) {
		this.accreditations = accreditations;
	}


	public List<TeachingStaff> listTeachingStaffs() {
		return teachingStaffs;
	}

	public void addTeachingStaffs(List<TeachingStaff> teachingStaffs) {
		this.teachingStaffs = teachingStaffs;
	}

	public List<TeachingSactionStrength> listTeachingSactionStrengths() {
		return teachingSactionStrengths;
	}

	public void addTeachingSactionStrengths(List<TeachingSactionStrength> teachingSactionStrengths) {
		this.teachingSactionStrengths = teachingSactionStrengths;
	}

	public Integer getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(Integer aisheCode) {
		this.aisheCode = aisheCode;
	}


	public FinanceIncome getFinanceIncome() {
		return financeIncome;
	}

	public void setFinanceIncome(FinanceIncome financeIncome) {
		this.financeIncome = financeIncome;
	}

	public Expenditure getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(Expenditure expenditure) {
		this.expenditure = expenditure;
	}

	public Infrastructure getInfrastructure() {
		return infrastructure;
	}

	public void setInfrastructure(Infrastructure infrastructure) {
		this.infrastructure = infrastructure;
	}

	public ScholarshipInfo getScholarshipInfo() {
		return scholarshipInfo;
	}

	public void setScholarshipInfo(ScholarshipInfo scholarshipInfo) {
		this.scholarshipInfo = scholarshipInfo;
	}

	public FellowshipInfo getFellowshipInfo() {
		return fellowshipInfo;
	}

	public void setFellowshipInfo(FellowshipInfo fellowshipInfo) {
		this.fellowshipInfo = fellowshipInfo;
	}

	public EducationLoan getEducationLoan() {
		return educationLoan;
	}

	public void setEducationLoan(EducationLoan educationLoan) {
		this.educationLoan = educationLoan;
	}

	public List<StaffCount> getStaffCounts() {
		return staffCounts;
	}

	public void setStaffCounts(List<StaffCount> staffCounts) {
		this.staffCounts = staffCounts;
	}

	public List<CourseEnrolledStudentCount> getCourseEnrolledStudentCounts() {
		return courseEnrolledStudentCounts;
	}

	public void setCourseEnrolledStudentCounts(List<CourseEnrolledStudentCount> courseEnrolledStudentCounts) {
		this.courseEnrolledStudentCounts = courseEnrolledStudentCounts;
	}

	public List<RegularStudentEnrollment> getRegularStudentEnrollments() {
		return regularStudentEnrollments;
	}

	public void setRegularStudentEnrollments(List<RegularStudentEnrollment> regularStudentEnrollments) {
		this.regularStudentEnrollments = regularStudentEnrollments;
	}

	public List<DistanceStudentEnrollment> getDistanceStudentEnrollments() {
		return distanceStudentEnrollments;
	}

	public void setDistanceStudentEnrollments(List<DistanceStudentEnrollment> distanceStudentEnrollments) {
		this.distanceStudentEnrollments = distanceStudentEnrollments;
	}

	public List<CourseEnrolledForeignStudentCount> getCourseEnrolledForeignStudentCounts() {
		return courseEnrolledForeignStudentCounts;
	}

	public void setCourseEnrolledForeignStudentCounts(
			List<CourseEnrolledForeignStudentCount> courseEnrolledForeignStudentCounts) {
		this.courseEnrolledForeignStudentCounts = courseEnrolledForeignStudentCounts;
	}

	public List<ForeignStudentEnrollment> getForeignStudentEnrollments() {
		return foreignStudentEnrollments;
	}

	public void setForeignStudentEnrollments(List<ForeignStudentEnrollment> foreignStudentEnrollments) {
		this.foreignStudentEnrollments = foreignStudentEnrollments;
	}

	public List<CourseExamResult> getCourseExamResults() {
		return courseExamResults;
	}

	public void setCourseExamResults(List<CourseExamResult> courseExamResults) {
		this.courseExamResults = courseExamResults;
	}

	public List<ExamResult> getExamResults() {
		return examResults;
	}

	public void setExamResults(List<ExamResult> examResults) {
		this.examResults = examResults;
	}

	public List<Accreditation> getAccreditations() {
		return accreditations;
	}

	public void setAccreditations(List<Accreditation> accreditations) {
		this.accreditations = accreditations;
	}

	public List<TeachingStaff> getTeachingStaffs() {
		return teachingStaffs;
	}

	public void setTeachingStaffs(List<TeachingStaff> teachingStaffs) {
		this.teachingStaffs = teachingStaffs;
	}

	public List<TeachingSactionStrength> getTeachingSactionStrengths() {
		return teachingSactionStrengths;
	}

	public void setTeachingSactionStrengths(List<TeachingSactionStrength> teachingSactionStrengths) {
		this.teachingSactionStrengths = teachingSactionStrengths;
	}

	public Boolean getWhetherAwardedByUniversity() {
		return whetherAwardedByUniversity;
	}

	public void setWhetherAwardedByUniversity(Boolean whetherAwardedByUniversity) {
		this.whetherAwardedByUniversity = whetherAwardedByUniversity;
	}

	public String getAwardedByUniversityId() {
		return awardedByUniversityId;
	}

	public void setAwardedByUniversityId(String awardedByUniversityId) {
		this.awardedByUniversityId = awardedByUniversityId;
	}

	public String getOtherDiploma() {
		return otherDiploma;
	}

	public void setOtherDiploma(String otherDiploma) {
		this.otherDiploma = otherDiploma;
	}

	public Boolean getIsMinorityManagedInstitution() {
		return isMinorityManagedInstitution;
	}

	public void setIsMinorityManagedInstitution(Boolean isMinorityManagedInstitution) {
		this.isMinorityManagedInstitution = isMinorityManagedInstitution;
	}

	public Integer getTypeMinorityCommunityManagingId() {
		return typeMinorityCommunityManagingId;
	}

	public void setTypeMinorityCommunityManagingId(Integer typeMinorityCommunityManagingId) {
		this.typeMinorityCommunityManagingId = typeMinorityCommunityManagingId;
	}

	public Boolean getIsNCC() {
		return isNCC;
	}

	public void setIsNCC(Boolean isNCC) {
		this.isNCC = isNCC;
	}

	public Integer getFemaleEnrolledNCC() {
		return femaleEnrolledNCC;
	}

	public void setFemaleEnrolledNCC(Integer femaleEnrolledNCC) {
		this.femaleEnrolledNCC = femaleEnrolledNCC;
	}

	public Integer getTotalEnrolledNCC() {
		return totalEnrolledNCC;
	}

	public void setTotalEnrolledNCC(Integer totalEnrolledNCC) {
		this.totalEnrolledNCC = totalEnrolledNCC;
	}

	public Boolean getIsNSS() {
		return isNSS;
	}

	public void setIsNSS(Boolean isNSS) {
		this.isNSS = isNSS;
	}

	public Integer getFemaleEnrolledNSS() {
		return femaleEnrolledNSS;
	}

	public void setFemaleEnrolledNSS(Integer femaleEnrolledNSS) {
		this.femaleEnrolledNSS = femaleEnrolledNSS;
	}

	public Integer getTotalEnrolledNSS() {
		return totalEnrolledNSS;
	}

	public void setTotalEnrolledNSS(Integer totalEnrolledNSS) {
		this.totalEnrolledNSS = totalEnrolledNSS;
	}

	public Integer getStudentEnrolledNCC() {
		return studentEnrolledNCC;
	}

	public void setStudentEnrolledNCC(Integer studentEnrolledNCC) {
		this.studentEnrolledNCC = studentEnrolledNCC;
	}

	public Integer getStudentEnrolledNSS() {
		return studentEnrolledNSS;
	}

	public void setStudentEnrolledNSS(Integer studentEnrolledNSS) {
		this.studentEnrolledNSS = studentEnrolledNSS;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	

}