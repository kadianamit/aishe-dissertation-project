package aishe.gov.in.masterseo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "public.university")
public class UniversityFormEO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private UniversityId universityID; 
	@EmbeddedId
	private UniversityEmadedPK universityPk;
	//@Column(name = "survey_year")
	//private Integer surveyYear;
	@Column(name = "name")
	private String name;	
	@Column(name = "address_line1")
	private String addressline1;
	@Column(name = "address_line2")
	private String addressline2;
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
	@Column(name = "is_university_uploaded_act_statues")
	private Boolean isUniversityUploadedActStatues;
	@Column(name = "is_university_complying_rules")
	private Boolean isUniversityComplyingRules;
	@Column(name = "is_university180_actual_teaching_days")
	private Boolean isUniversity180ActualTeachingDays;
	@Column(name = "has_foreign_teachers")
	private Boolean hasForeignTeachers;
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
	@Column(name = "status_prior_to_establishment")
	private String statusPriorToEstablishment;
	@Column(name = "ownership_status")
	private Integer ownershipStatus;
	@Column(name = "whether_village_adopted_under_unnat_bharat")
	private Boolean whetherVillageAdoptedUnderUnnatBharat;
	@Column(name = "boys_exclusive")
	private Boolean boysExclusive;
	@Column(name = "inclusion_as_university_under_section",insertable=false ,updatable=false)
	private String inclusionAsUniversityUnderSection;
	@Column(name = "inclusion_as_university_date",insertable=false ,updatable=false)
	private Date inclusionAsUniversityDate;
	@Column(name = "placement_details_id")
	private Integer placementDetailsId;
//	@ManyToOne
//	@NotFound(action = NotFoundAction.IGNORE)
//	@JoinColumn(name = "nodal_officer_id", insertable = false, updatable = false)
//	private NodalOfficerEO nodalOfficer;
	//@ManyToOne
	//@JoinColumn(name = "staff_quarter_id", insertable = false, updatable = false)
	//private StaffQuarterEO staffQuarter;
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "district_code", insertable = false, updatable = false)
	private RefDistrict districtCode;
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "state_code", insertable = false, updatable = false)
	private RefState stateCode;
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "type_id", insertable = false, updatable = false)
	private RefUniversityType instituteType;
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "speciality_id", insertable = false, updatable = false)
	private RefSpecialityEO speciality;
//	@Column(name = "speciality_id")
//	private String speciality;
	//@Transient
	//private List<StudentHostelEO> hostels;
	
	//new changes
	@Column(name = "inclusion_as_university_under_section")
	private String inclusion_as_university_under_section;
	@Column(name = "inclusion_as_university_date")
	private Date inclusion_as_university_date;
	@Column(name = "approved_intake_capacity_intl_nri_pio_students")
	private Integer approved_intake_capacity_intl_nri_pio_students;
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "placement_details_id", insertable = false, updatable = false)
	private PlacementDetails placementDetails;
	@Transient
	@NotFound(action = NotFoundAction.IGNORE)
	private String inclusionAsUniversityDateString;
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
//	@Column(name = "no_of_student_enrolled_in_ncc")
//	private Integer studentEnrolledNCC;
//	@Column(name = "no_of_student_enrolled_in_nss")
//	private Integer studentEnrolledNSS;
	@Column(name = "ncc_own_institute_male")
	private Integer nccOwnInstituteMale;
	@Column(name = "ncc_own_institute_female")
	private Integer nccOwnInstituteFemale;
	@Column(name = "ncc_other_institute_male")
	private Integer nccOtherInstituteMale;
	@Column(name = "ncc_other_institute_female")
	private Integer nccOtherInstituteFemale;
	@Column(name = "no_of_nss_male")
	private Integer noOfNssMale;
	@Column(name = "placement_details_available")
	private Boolean placementDetailsAvailable;
	@Column(name = "block_id")
	private Integer blockId;
	@Column(name = "ulb_id")
	private Integer ulbId;
	@Column(name = "offers_private_programme")
	private Boolean programmeThroughPrivateExternal;
	
}