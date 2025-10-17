package org.nicexam.authorizationservice.enums;

public enum OfficeSearchParameter {
	ROOT("ROOT"), PARENT("PARENT"),CHILD("CHILD");	
	
	private String mesaage;
	 
	OfficeSearchParameter(String message) {
		this.mesaage = message;
	}

	public String getMessage() {
		return mesaage;
	}
	
	public static OfficeSearchParameter parse(String val) {
		for(OfficeSearchParameter msg : OfficeSearchParameter.values()) {
			if(msg.getMessage().equals(val)) {
				return msg;
			}
		}
		return null;	
	}
}
