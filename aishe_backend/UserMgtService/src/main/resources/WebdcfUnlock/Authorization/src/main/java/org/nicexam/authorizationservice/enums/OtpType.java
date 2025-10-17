package org.nicexam.authorizationservice.enums;

public enum OtpType {
	EMAIL("EMAIL"), MOBILE("MOBILE");
	
	private String mesaage;
	 
	OtpType(String message) {
		this.mesaage = message;
	}

	public String getMessage() {
		return mesaage;
	}
	
	public static OtpType parse(String val) {
		for(OtpType msg : OtpType.values()) {
			if(msg.getMessage().equals(val)) {
				return msg;
			}
		}
		return null;	
	}
}
