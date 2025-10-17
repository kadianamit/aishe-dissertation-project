package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
	
	@JsonProperty
	private String premiseNo;
	@JsonProperty
	private String subLocality;
	@JsonProperty
	private String locality;
	@JsonProperty
	private String district;
	@JsonProperty
	private String pinCode;
	@JsonProperty
	private String state;
	@JsonProperty
	private String website;
	@JsonProperty
	private String totalAreaInAcre;
	@JsonProperty
	private String totalConstructedAreaInSqm;
	                                                     
	
	public String getPremiseNo() {
		return premiseNo;
	}
	public void setPremiseNo(String premiseNo) {
		this.premiseNo = premiseNo;
	}
	public String getSubLocality() {
		return subLocality;
	}
	public void setSubLocality(String subLocality) {
		this.subLocality = subLocality;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTotalAreaInAcre() {
		return totalAreaInAcre;
	}
	public void setTotalAreaInAcre(String totalAreaInAcre) {
		this.totalAreaInAcre = totalAreaInAcre;
	}
	public String getTotalConstructedAreaInSqm() {
		return totalConstructedAreaInSqm;
	}
	public void setTotalConstructedAreaInSqm(String totalConstructedAreaInSqm) {
		this.totalConstructedAreaInSqm = totalConstructedAreaInSqm;
	}
	

	
}
