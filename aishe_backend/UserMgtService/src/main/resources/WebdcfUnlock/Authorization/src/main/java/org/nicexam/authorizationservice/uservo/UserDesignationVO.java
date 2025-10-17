package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
public class UserDesignationVO implements Serializable {

	private static final long serialVersionUID = 7410551910588605891L;

	private Integer recordId;

	private String designationName;

	private String designationDescription;
	
    private String officeLevel;
	
	private String ebaName;
	
	private Integer userOfficeId;
	
	private String userOfficeName;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getDesignationDescription() {
		return designationDescription;
	}

	public void setDesignationDescription(String designationDescription) {
		this.designationDescription = designationDescription;
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

	public Integer getUserOfficeId() {
		return userOfficeId;
	}

	public void setUserOfficeId(Integer userOfficeId) {
		this.userOfficeId = userOfficeId;
	}

	public String getUserOfficeName() {
		return userOfficeName;
	}

	public void setUserOfficeName(String userOfficeName) {
		this.userOfficeName = userOfficeName;
	}


}
