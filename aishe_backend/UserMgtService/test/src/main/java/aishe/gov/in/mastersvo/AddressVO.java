package aishe.gov.in.mastersvo;

import aishe.gov.in.masterseo.RefDistrict;
import aishe.gov.in.masterseo.RefState;

public class AddressVO {
	
	private String aisheCode;
	
	private Integer surveyYear;

	private String addressline1;

	private String addressline2;

	private Double area;

	private String city;
	
	private Double constructArea;
	
	private String blockCityTown;
	
	private Double latitude;

	private Double longitude;

	private RefState stateCode;

	private RefDistrict districtCode;

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getConstructArea() {
		return constructArea;
	}

	public void setConstructArea(Double constructArea) {
		this.constructArea = constructArea;
	}

	public String getBlockCityTown() {
		return blockCityTown;
	}

	public void setBlockCityTown(String blockCityTown) {
		this.blockCityTown = blockCityTown;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public RefState getStateCode() {
		return stateCode;
	}

	public void setStateCode(RefState stateCode) {
		this.stateCode = stateCode;
	}

	public RefDistrict getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(RefDistrict districtCode) {
		this.districtCode = districtCode;
	}

	public String getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	
}
