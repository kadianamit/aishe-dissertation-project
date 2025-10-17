package aishe.gov.in.utility;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnResponseNew {

	private String message;

	private String status;

	private Object data;

	public ReturnResponseNew(String status, String message) {
		super();
		this.message = message;
		this.status = status;
	}

	public ReturnResponseNew(String status, String message, Object data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}

    public ReturnResponseNew(String unauthorizedUser, int value, Object data) {
		this.message = message;
		this.status = status;
		this.data = data;
    }

    // General error message about nature of error

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
