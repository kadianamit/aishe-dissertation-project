package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialIncomeVO {
	
	@JsonProperty
	private Double ugcGrants;
	@JsonProperty
	private Double decGrants;
	@JsonProperty
	private Double ogdGrants;
	@JsonProperty
	private Double stateGovGrants;
	@JsonProperty
	private Double universityGrants;
	@JsonProperty
	private Double grantsFromLocalBoards;
	@JsonProperty
	private Double donations;
	@JsonProperty
	private Double tuitionFee;
	@JsonProperty
	private Double otherFees;
	@JsonProperty
	private Double interests;
	@JsonProperty
	private Double formSale;
	@JsonProperty
	private Double otherIncome;
	@JsonProperty
	private Double total;
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
