package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Econtact {
	@JsonProperty
	private String contactType;
	@JsonProperty
	private String  contactValue;
	
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getContactValue() {
		return contactValue;
	}
	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}
}
