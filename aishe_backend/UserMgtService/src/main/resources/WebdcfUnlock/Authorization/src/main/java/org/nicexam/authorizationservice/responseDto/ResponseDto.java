package org.nicexam.authorizationservice.responseDto;

import java.io.Serializable;

public class ResponseDto implements Serializable{

	/**
	 *  Virendra Gupta
	 */
	private static final long serialVersionUID = 786090966213172041L;
	private int id;
	private String value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
