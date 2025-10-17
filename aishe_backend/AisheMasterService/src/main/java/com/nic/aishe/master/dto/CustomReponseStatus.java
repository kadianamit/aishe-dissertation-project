package com.nic.aishe.master.dto;

public class CustomReponseStatus {


	private Long responseCode;
	private String responseMessage;
	private String responseDescription;
	private Long responseId;
	
	public CustomReponseStatus(Long responseId, Long responseCode, String responseMessage, String responseDescription) {
		this.responseId = responseId;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.responseDescription = responseDescription;
	}
	
	public long getResponseId() {
		return responseId;
	}

	public void setResponseId(long responseId) {
		this.responseId = responseId;
	}

	public void setResponseCode(long responseCode) {
		this.responseCode = responseCode;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public long getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public String toString() {
		return new StringBuilder().append("responseCode : ").append("" + responseCode).append(" , responseMessage : ")
				.append(responseMessage).append(" , responseDescription : ").append(responseDescription)
				.append(" , responseId : ").append(responseId).toString();
	}


}
