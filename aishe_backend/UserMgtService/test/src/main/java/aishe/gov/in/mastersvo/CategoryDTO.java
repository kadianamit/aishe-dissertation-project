package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO {
	
	@JsonProperty
	private String categoryName;
	@JsonProperty
	private NumberInPossitionDTO noOfStudentAvailingLoans;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public NumberInPossitionDTO getNoOfStudentAvailingLoans() {
		return noOfStudentAvailingLoans;
	}
	public void setNoOfStudentAvailingLoans(NumberInPossitionDTO noOfStudentAvailingLoans) {
		this.noOfStudentAvailingLoans = noOfStudentAvailingLoans;
	}

	
}
