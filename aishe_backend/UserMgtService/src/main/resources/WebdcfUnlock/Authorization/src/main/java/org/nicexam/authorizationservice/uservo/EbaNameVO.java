package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class EbaNameVO implements Serializable {

	private static final long serialVersionUID = 6312637720187937681L;

	private int recordId;

	private String ebaName;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getEbaName() {
		return ebaName;
	}

	public void setEbaName(String ebaName) {
		this.ebaName = ebaName;
	}
}
