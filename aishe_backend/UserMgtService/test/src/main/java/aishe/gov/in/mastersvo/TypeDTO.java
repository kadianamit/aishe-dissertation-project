package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeDTO {
	
	@JsonProperty
	private String typeName;
	@JsonProperty
	private NumberInPossitionDTO numberInPossition;
	
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public NumberInPossitionDTO getNumberInPossition() {
		return numberInPossition;
	}
	public void setNumberInPossition(NumberInPossitionDTO numberInPossition) {
		this.numberInPossition = numberInPossition;
	}
	
	
	

}
