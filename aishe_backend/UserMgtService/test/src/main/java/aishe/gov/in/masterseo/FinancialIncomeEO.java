package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "financial_income")
public class FinancialIncomeEO {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "grants_from_university_grants_commission")
	private Double ugcGrants;
	@Column(name = "grants_from_distance_education_council")
	private Double decGrants;
	@Column(name = "grants_from_other_central_govt_dept")
	private Double ogdGrants;
	@Column(name = "grants_from_state_govt")
	private Double stateGovGrants;
	@Column(name = "grants_from_university")
	private Double universityGrants;
	@Column(name = "grants_from_local_boards")
	private Double grantsFromLocalBoards;
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
	@Column(name = "total")
	private Double total;
	
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
	public Double getUniversityGrants() {
		return universityGrants;
	}
	public void setUniversityGrants(Double universityGrants) {
		this.universityGrants = universityGrants;
	}
	public Double getGrantsFromLocalBoards() {
		return grantsFromLocalBoards;
	}
	public void setGrantsFromLocalBoards(Double grantsFromLocalBoards) {
		this.grantsFromLocalBoards = grantsFromLocalBoards;
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
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
		
}
