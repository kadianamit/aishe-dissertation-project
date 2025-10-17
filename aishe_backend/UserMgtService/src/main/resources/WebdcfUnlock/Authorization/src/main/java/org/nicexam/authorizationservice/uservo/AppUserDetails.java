package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.util.List;

public class AppUserDetails implements Serializable {

	private static final long serialVersionUID = -1575450863372107320L;

	private int recordId;

	private String name;
	
	private String loginId;

	private String emailId;

	private String mobileNo;
	
    private String officeLevel;
	
	private String ebaName;
	
	List<UserDesignationVO> designationList;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOfficeLevel() {
		return officeLevel;
	}

	public void setOfficeLevel(String officeLevel) {
		this.officeLevel = officeLevel;
	}

	public String getEbaName() {
		return ebaName;
	}

	public void setEbaName(String ebaName) {
		this.ebaName = ebaName;
	}

	public List<UserDesignationVO> getDesignationList() {
		return designationList;
	}

	public void setDesignationList(List<UserDesignationVO> designationList) {
		this.designationList = designationList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}