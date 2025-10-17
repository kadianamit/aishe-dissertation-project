package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "financial_income")
public class FinanceIncome {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/*@Transient
	private Integer surveyYear;
	@Transient
	private Integer aisheCode;*/
	@Column(name = "survey_year")
	private Integer surveyYear;

	@Column(name = "aishe_code")
	private Integer aisheCode;
	@Column(name="institute_type")
	private String instituteType;
	@Column(name = "grants_from_university_grants_commission")
	private Double ugcGrants;

	@Column(name = "grants_from_distance_education_council")
	private Double decGrants;

	public Double getLocalGrants() {
		return localGrants;
	}

	public void setLocalGrants(Double localGrants) {
		this.localGrants = localGrants;
	}

	@Column(name = "grants_from_other_central_govt_dept")
	private Double ogdGrants;

	@Column(name = "grants_from_state_govt")
	private Double stateGovGrants;
	
	@Column(name = "grants_from_local_boards")
	private Double localGrants;

	@Column(name = "donations")
	private Double donations;

	@Column(name = "tuition_fee")
	private Double tuitionFee;

	@Column(name = "other_fees")
	private Double otherFees;

	@Column(name = "interests")
	private Double interests;

	@Column(name = "sale_of_application_form")
	private Double formSale;
	@Column(name = "other_income")
	private Double otherIncome;

	@Column(name = "grants_from_university")
	private Double universityGrants;
	
	@Column(name = "total",nullable = false)
	private Double total;

	@Column(name = "tution_fee_indian_students_regular")
	private Double tutionFeeIndianStudentsRegular;

	@Column(name = "tution_fee_foreign_students_regular")
	private Double tutionFeeForeignStudentsRegular;

	@Column(name = "tution_fee_indian_students_distance")
	private Double tutionFeeIndianStudentsDistance;

	@Column(name = "tution_fee_foreign_students_distance_mode")
	private Double tutionFeeForeignStudentsDistanceMode;

	@Column(name = "other_income_indian_students_lodging_boarding")
	private Double otherIncomeIndianStudentsLodgingBoarding;

	@Column(name = "other_income_foreign_students_lodging_boarding")
	private Double otherIncomeForeignStudentsLodgingBoarding;

	@Column(name = "other_income_faculty_visiting_abroad")
	private Double otherIncomeFacultyVisitingAbroad;

	@Column(name = "other_income_other_sources")
	private Double otherIncomeOtherSources;

	@Transient
	private String ugcGrantsString;
	@Transient
	private String decGrantsString;
	@Transient
	private String ogdGrantsString;
	@Transient
	private String stateGovGrantsString;
	@Transient
	private String universityGrantsString;
	@Transient
	private String grantsFromLocalBoardsString;
	@Transient
	private String donationsString;
	@Transient
	private String tuitionFeeString;
	@Transient
	private String otherFeesString;
	@Transient
	private String interestsString;
	@Transient
	private String formSaleString;
	@Transient
	private String otherIncomeString;
	@Transient
	private String totalString;
	@Transient
	private String tutionFeeIndianStudentsRegularString;
	@Transient
	private String tutionFeeForeignStudentsRegularString;
	@Transient
	private String tutionFeeIndianStudentsDistanceString;
	@Transient
	private String tutionFeeForeignStudentsDistanceModeString;
	@Transient
	private String otherIncomeIndianStudentsLodgingBoardingString;
	@Transient
	private String otherIncomeForeignStudentsLodgingBoardingString;
	@Transient
	private String otherIncomeFacultyVisitingAbroadString;
	@Transient
	private String otherIncomeOtherSourcesString;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getUgcGrants() {
		return ugcGrants;
	}

	public void setUgcGrants(Double ugcGrants) {
		this.ugcGrants = ugcGrants;
	}

	public Double getDecGrants() {
		return decGrants;
	}

	public void setDecGrants(Double decGrants) {
		this.decGrants = decGrants;
	}

	public Double getOgdGrants() {
		return ogdGrants;
	}

	public void setOgdGrants(Double ogdGrants) {
		this.ogdGrants = ogdGrants;
	}

	public Double getStateGovGrants() {
		return stateGovGrants;
	}

	public void setStateGovGrants(Double stateGovGrants) {
		this.stateGovGrants = stateGovGrants;
	}

	public Double getDonations() {
		return donations;
	}

	public void setDonations(Double donations) {
		this.donations = donations;
	}

	public Double getTuitionFee() {
		return tuitionFee;
	}

	public void setTuitionFee(Double tuitionFee) {
		this.tuitionFee = tuitionFee;
	}

	public Double getOtherFees() {
		return otherFees;
	}

	public void setOtherFees(Double otherFees) {
		this.otherFees = otherFees;
	}

	public Double getInterests() {
		return interests;
	}

	public void setInterests(Double interests) {
		this.interests = interests;
	}

	public Double getFormSale() {
		return formSale;
	}

	public void setFormSale(Double formSale) {
		this.formSale = formSale;
	}

	public Double getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}

	public Double getUniversityGrants() {
		return universityGrants;
	}

	public void setUniversityGrants(Double universityGrants) {
		this.universityGrants = universityGrants;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

	public Integer getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(Integer aisheCode) {
		this.aisheCode = aisheCode;
	}

	public Double getTutionFeeIndianStudentsRegular() {
		return tutionFeeIndianStudentsRegular;
	}

	public void setTutionFeeIndianStudentsRegular(Double tutionFeeIndianStudentsRegular) {
		this.tutionFeeIndianStudentsRegular = tutionFeeIndianStudentsRegular;
	}

	public Double getTutionFeeForeignStudentsRegular() {
		return tutionFeeForeignStudentsRegular;
	}

	public void setTutionFeeForeignStudentsRegular(Double tutionFeeForeignStudentsRegular) {
		this.tutionFeeForeignStudentsRegular = tutionFeeForeignStudentsRegular;
	}

	public Double getTutionFeeIndianStudentsDistance() {
		return tutionFeeIndianStudentsDistance;
	}

	public void setTutionFeeIndianStudentsDistance(Double tutionFeeIndianStudentsDistance) {
		this.tutionFeeIndianStudentsDistance = tutionFeeIndianStudentsDistance;
	}

	public Double getTutionFeeForeignStudentsDistanceMode() {
		return tutionFeeForeignStudentsDistanceMode;
	}

	public void setTutionFeeForeignStudentsDistanceMode(Double tutionFeeForeignStudentsDistanceMode) {
		this.tutionFeeForeignStudentsDistanceMode = tutionFeeForeignStudentsDistanceMode;
	}

	public Double getOtherIncomeIndianStudentsLodgingBoarding() {
		return otherIncomeIndianStudentsLodgingBoarding;
	}

	public void setOtherIncomeIndianStudentsLodgingBoarding(Double otherIncomeIndianStudentsLodgingBoarding) {
		this.otherIncomeIndianStudentsLodgingBoarding = otherIncomeIndianStudentsLodgingBoarding;
	}

	public Double getOtherIncomeForeignStudentsLodgingBoarding() {
		return otherIncomeForeignStudentsLodgingBoarding;
	}

	public void setOtherIncomeForeignStudentsLodgingBoarding(Double otherIncomeForeignStudentsLodgingBoarding) {
		this.otherIncomeForeignStudentsLodgingBoarding = otherIncomeForeignStudentsLodgingBoarding;
	}

	public Double getOtherIncomeFacultyVisitingAbroad() {
		return otherIncomeFacultyVisitingAbroad;
	}

	public void setOtherIncomeFacultyVisitingAbroad(Double otherIncomeFacultyVisitingAbroad) {
		this.otherIncomeFacultyVisitingAbroad = otherIncomeFacultyVisitingAbroad;
	}

	public Double getOtherIncomeOtherSources() {
		return otherIncomeOtherSources;
	}

	public void setOtherIncomeOtherSources(Double otherIncomeOtherSources) {
		this.otherIncomeOtherSources = otherIncomeOtherSources;
	}

	public String getUgcGrantsString() {
		return ugcGrantsString;
	}

	public void setUgcGrantsString(String ugcGrantsString) {
		this.ugcGrantsString = ugcGrantsString;
	}

	public String getDecGrantsString() {
		return decGrantsString;
	}

	public void setDecGrantsString(String decGrantsString) {
		this.decGrantsString = decGrantsString;
	}

	public String getOgdGrantsString() {
		return ogdGrantsString;
	}

	public void setOgdGrantsString(String ogdGrantsString) {
		this.ogdGrantsString = ogdGrantsString;
	}

	public String getStateGovGrantsString() {
		return stateGovGrantsString;
	}

	public void setStateGovGrantsString(String stateGovGrantsString) {
		this.stateGovGrantsString = stateGovGrantsString;
	}

	public String getUniversityGrantsString() {
		return universityGrantsString;
	}

	public void setUniversityGrantsString(String universityGrantsString) {
		this.universityGrantsString = universityGrantsString;
	}

	public String getGrantsFromLocalBoardsString() {
		return grantsFromLocalBoardsString;
	}

	public void setGrantsFromLocalBoardsString(String grantsFromLocalBoardsString) {
		this.grantsFromLocalBoardsString = grantsFromLocalBoardsString;
	}

	public String getDonationsString() {
		return donationsString;
	}

	public void setDonationsString(String donationsString) {
		this.donationsString = donationsString;
	}

	public String getTuitionFeeString() {
		return tuitionFeeString;
	}

	public void setTuitionFeeString(String tuitionFeeString) {
		this.tuitionFeeString = tuitionFeeString;
	}

	public String getOtherFeesString() {
		return otherFeesString;
	}

	public void setOtherFeesString(String otherFeesString) {
		this.otherFeesString = otherFeesString;
	}

	public String getInterestsString() {
		return interestsString;
	}

	public void setInterestsString(String interestsString) {
		this.interestsString = interestsString;
	}

	public String getFormSaleString() {
		return formSaleString;
	}

	public void setFormSaleString(String formSaleString) {
		this.formSaleString = formSaleString;
	}

	public String getOtherIncomeString() {
		return otherIncomeString;
	}

	public void setOtherIncomeString(String otherIncomeString) {
		this.otherIncomeString = otherIncomeString;
	}

	public String getTotalString() {
		return totalString;
	}

	public void setTotalString(String totalString) {
		this.totalString = totalString;
	}

	public String getTutionFeeIndianStudentsRegularString() {
		return tutionFeeIndianStudentsRegularString;
	}

	public void setTutionFeeIndianStudentsRegularString(String tutionFeeIndianStudentsRegularString) {
		this.tutionFeeIndianStudentsRegularString = tutionFeeIndianStudentsRegularString;
	}

	public String getTutionFeeForeignStudentsRegularString() {
		return tutionFeeForeignStudentsRegularString;
	}

	public void setTutionFeeForeignStudentsRegularString(String tutionFeeForeignStudentsRegularString) {
		this.tutionFeeForeignStudentsRegularString = tutionFeeForeignStudentsRegularString;
	}

	public String getTutionFeeIndianStudentsDistanceString() {
		return tutionFeeIndianStudentsDistanceString;
	}

	public void setTutionFeeIndianStudentsDistanceString(String tutionFeeIndianStudentsDistanceString) {
		this.tutionFeeIndianStudentsDistanceString = tutionFeeIndianStudentsDistanceString;
	}

	public String getTutionFeeForeignStudentsDistanceModeString() {
		return tutionFeeForeignStudentsDistanceModeString;
	}

	public void setTutionFeeForeignStudentsDistanceModeString(String tutionFeeForeignStudentsDistanceModeString) {
		this.tutionFeeForeignStudentsDistanceModeString = tutionFeeForeignStudentsDistanceModeString;
	}

	public String getOtherIncomeIndianStudentsLodgingBoardingString() {
		return otherIncomeIndianStudentsLodgingBoardingString;
	}

	public void setOtherIncomeIndianStudentsLodgingBoardingString(String otherIncomeIndianStudentsLodgingBoardingString) {
		this.otherIncomeIndianStudentsLodgingBoardingString = otherIncomeIndianStudentsLodgingBoardingString;
	}

	public String getOtherIncomeForeignStudentsLodgingBoardingString() {
		return otherIncomeForeignStudentsLodgingBoardingString;
	}

	public void setOtherIncomeForeignStudentsLodgingBoardingString(String otherIncomeForeignStudentsLodgingBoardingString) {
		this.otherIncomeForeignStudentsLodgingBoardingString = otherIncomeForeignStudentsLodgingBoardingString;
	}

	public String getOtherIncomeFacultyVisitingAbroadString() {
		return otherIncomeFacultyVisitingAbroadString;
	}

	public void setOtherIncomeFacultyVisitingAbroadString(String otherIncomeFacultyVisitingAbroadString) {
		this.otherIncomeFacultyVisitingAbroadString = otherIncomeFacultyVisitingAbroadString;
	}

	public String getOtherIncomeOtherSourcesString() {
		return otherIncomeOtherSourcesString;
	}

	public void setOtherIncomeOtherSourcesString(String otherIncomeOtherSourcesString) {
		this.otherIncomeOtherSourcesString = otherIncomeOtherSourcesString;
	}

	public String getInstituteType() {
		return instituteType;
	}

	public void setInstituteType(String instituteType) {
		this.instituteType = instituteType;
	}
	
}
