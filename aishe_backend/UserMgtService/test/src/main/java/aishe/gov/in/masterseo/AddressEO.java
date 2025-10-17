package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.address")
public class AddressEO {
	
	@Id
	@Column(name = "premise_no")
	private String premiseNo;
	@Column(name = "sub_locality")
	private String subLocality;
	@Column(name = "locality")
	private String locality;
	@Column(name = "district")
	private String district;
	@Column(name = "pinCode")
	private String pinCode;
	@Column(name = "state")
	private String state;
	@Column(name = "website")
	private String website;
	@Column(name = "total_area_in_acre")
	private String totalAreaInAcre;
	@Column(name = "total_constructed_area_in_sqm")
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
