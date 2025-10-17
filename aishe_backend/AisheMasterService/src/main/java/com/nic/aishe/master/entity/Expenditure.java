package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="financial_expenditure")
public class Expenditure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	//@Transient
	private Integer surveyYear;
	//@Transient
	private Integer aisheCode;
	@Column(name="institute_type")
	private String instituteType;
	@Column(name = "salary")
	private Double salary;

	@Column(name = "buildings")
	private Double buildings;

	@Column(name = "library_and_laboratory")
	private Double libraryLaboratory;

	@Column(name = "scholorships")
	private Double scholorships;

	@Column(name = "grants_to_colleges")
	private Double collegesGrants;

	@Column(name = "other_expenses")
	private Double otherExpenses;
	
	@Column(name = "total",nullable = false)
	private Double total;
	
	@Column(name = "research_activities")
	private Double researchActivity;

	@Transient
	private String salaryString;
	@Transient
	private String buildingsString;
	@Transient
	private String libraryLaboratoryString;
	@Transient
	private String researchActivityString;
	@Transient
	private String scholorshipsString;
	@Transient
	private String collegesGrantsString;
	@Transient
	private String otherExpensesString;
	@Transient
	private String totalString;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getBuildings() {
		return buildings;
	}

	public void setBuildings(Double buildings) {
		this.buildings = buildings;
	}

	public Double getLibraryLaboratory() {
		return libraryLaboratory;
	}

	public void setLibraryLaboratory(Double libraryLaboratory) {
		this.libraryLaboratory = libraryLaboratory;
	}

	public Double getScholorships() {
		return scholorships;
	}

	public void setScholorships(Double scholorships) {
		this.scholorships = scholorships;
	}

	public Double getCollegesGrants() {
		return collegesGrants;
	}

	public void setCollegesGrants(Double collegesGrants) {
		this.collegesGrants = collegesGrants;
	}

	public Double getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(Double otherExpenses) {
		this.otherExpenses = otherExpenses;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getResearchActivity() {
		return researchActivity;
	}

	public void setResearchActivity(Double researchActivity) {
		this.researchActivity = researchActivity;
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

	public String getSalaryString() {
		return salaryString;
	}

	public void setSalaryString(String salaryString) {
		this.salaryString = salaryString;
	}

	public String getBuildingsString() {
		return buildingsString;
	}

	public void setBuildingsString(String buildingsString) {
		this.buildingsString = buildingsString;
	}

	public String getLibraryLaboratoryString() {
		return libraryLaboratoryString;
	}

	public void setLibraryLaboratoryString(String libraryLaboratoryString) {
		this.libraryLaboratoryString = libraryLaboratoryString;
	}

	public String getResearchActivityString() {
		return researchActivityString;
	}

	public void setResearchActivityString(String researchActivityString) {
		this.researchActivityString = researchActivityString;
	}

	public String getScholorshipsString() {
		return scholorshipsString;
	}

	public void setScholorshipsString(String scholorshipsString) {
		this.scholorshipsString = scholorshipsString;
	}

	public String getCollegesGrantsString() {
		return collegesGrantsString;
	}

	public void setCollegesGrantsString(String collegesGrantsString) {
		this.collegesGrantsString = collegesGrantsString;
	}

	public String getOtherExpensesString() {
		return otherExpensesString;
	}

	public void setOtherExpensesString(String otherExpensesString) {
		this.otherExpensesString = otherExpensesString;
	}

	public String getTotalString() {
		return totalString;
	}

	public void setTotalString(String totalString) {
		this.totalString = totalString;
	}

	public String getInstituteType() {
		return instituteType;
	}

	public void setInstituteType(String instituteType) {
		this.instituteType = instituteType;
	}
	


}
