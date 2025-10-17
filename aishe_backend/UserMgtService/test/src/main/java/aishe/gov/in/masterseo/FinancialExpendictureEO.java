package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "financial_expenditure")
public class FinancialExpendictureEO {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "salary")
	private Double salary;
	@Column(name = "buildings")
	private Double buildings;
	@Column(name = "library_and_laboratory")
	private Double libraryLaboratory;
	@Column(name = "research_activities")
	private Double researchActivity;
	@Column(name = "scholorships")
	private Double scholorships;
	@Column(name = "grants_to_colleges")
	private Double collegesGrants;
	@Column(name = "other_expenses")
	private Double otherExpenses;
	@Column(name = "total")
	private Double total;
	
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
