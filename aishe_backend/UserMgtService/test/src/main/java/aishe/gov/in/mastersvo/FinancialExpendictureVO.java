package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialExpendictureVO {
	@JsonProperty
	private Double salary;
	@JsonProperty
	private Double buildings;
	@JsonProperty
	private Double libraryLaboratory;
	@JsonProperty
	private Double researchActivity;
	@JsonProperty
	private Double scholorships;
	@JsonProperty
	private Double collegesGrants;
	@JsonProperty
	private Double otherExpenses;
	@JsonProperty
	private Double total;
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
	public Double getResearchActivity() {
		return researchActivity;
	}
	public void setResearchActivity(Double researchActivity) {
		this.researchActivity = researchActivity;
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
}
