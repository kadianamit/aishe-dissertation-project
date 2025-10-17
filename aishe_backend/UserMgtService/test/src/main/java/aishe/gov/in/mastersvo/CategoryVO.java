package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryVO {

	@JsonProperty
	private String categoryName;
	@JsonProperty
	private NumberInPossitionDTO numberInPossition;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public NumberInPossitionDTO getNumberInPossition() {
		return numberInPossition;
	}
	public void setNumberInPossition(NumberInPossitionDTO numberInPossition) {
		this.numberInPossition = numberInPossition;
	}
	
	
	
}
