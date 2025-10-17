package org.nicexam.authorizationservice.enums;

public enum Activities {
	LOGIN_SUCCESSFUL("login successful"), LOGIN_FAILED("login failed"),ERROR("error");	
	
	private String mesaage;
	 
	Activities(String message) {
		this.mesaage = message;
	}

	public String getMessage() {
		return mesaage;
	}
	
	public static Activities parse(String val) {
		for(Activities msg : Activities.values()) {
			if(msg.getMessage().equals(val)) {
				return msg;
			}
		}
		return null;	
	}
}
