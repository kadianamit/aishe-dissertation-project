package aishe.gov.in.utility;

public class ReturnResponse {

	public ReturnResponse(int status,String message) {
		super();
		this.message = message;
		this.status = status;
	}

	// General error message about nature of error
	private String message;
	
	private int status;

	
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

	
}
