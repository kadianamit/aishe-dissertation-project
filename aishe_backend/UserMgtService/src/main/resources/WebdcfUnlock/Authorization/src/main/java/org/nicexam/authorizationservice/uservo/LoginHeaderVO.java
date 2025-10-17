package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class LoginHeaderVO implements Serializable {

	private static final long serialVersionUID = 1879482545735914266L;

	private int recordId;
	
	private String header;
	
	private String address;


	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}