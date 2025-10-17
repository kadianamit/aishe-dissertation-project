package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class OfficeTypeVO implements Serializable {

	private static final long serialVersionUID = 8484096644688515695L;

	private int recordId;
	
	private String officeType;
	

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	
	public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}
}