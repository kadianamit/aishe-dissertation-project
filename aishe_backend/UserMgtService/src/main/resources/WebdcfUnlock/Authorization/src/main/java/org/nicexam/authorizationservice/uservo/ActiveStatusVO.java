package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
public class ActiveStatusVO implements Serializable {

	private static final long serialVersionUID = 3157808092872607072L;

	private int recordId;

	private String Status;

	private boolean isActive;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}	
}