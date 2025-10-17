package com.nic.aishe.master.util;

import java.util.List;

public class ErrorResponse {

	public ErrorResponse(int status, String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
		this.status = status;
		
	}

	// General error message about nature of error
	private String message;
	
	private int status;

	// Specific errors in API request processing
	private List<String> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
