package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class OfficeAcronymVO implements Serializable {

	private static final long serialVersionUID = -6477197366136523102L;

	private Integer recordId;
	
	private String officeName;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

}