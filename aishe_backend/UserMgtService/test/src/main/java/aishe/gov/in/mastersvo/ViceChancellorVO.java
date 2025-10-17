package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViceChancellorVO {

	@JsonProperty
	private String name;
	
	@JsonProperty
	private String contactNumber;
	@JsonProperty
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
