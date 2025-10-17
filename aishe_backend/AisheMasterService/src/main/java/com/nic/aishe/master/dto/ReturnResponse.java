package com.nic.aishe.master.dto;

public class ReturnResponse {

	public ReturnResponse(int status, String message, Object data) {
		super();
		this.message = message;
		this.status = status;
		this.data=data;
	}

	private String message;
	
	private int status;

	private Object data;

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
