package aishe.gov.in.masterseo;

import javax.persistence.Column;


public class StaffDetails {
    @Column
	private String countryName;
    @Column
    private String passportNumber;
    @Column
    private String school;
    @Column
    private String departmentName;
    @Column
    private String nameOfTheEmployee;
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getNameOfTheEmployee() {
		return nameOfTheEmployee;
	}
	public void setNameOfTheEmployee(String nameOfTheEmployee) {
		this.nameOfTheEmployee = nameOfTheEmployee;
	}
   
   
}
