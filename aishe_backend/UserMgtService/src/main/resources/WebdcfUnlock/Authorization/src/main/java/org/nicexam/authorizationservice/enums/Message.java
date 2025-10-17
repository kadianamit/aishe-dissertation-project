package org.nicexam.authorizationservice.enums;

public enum Message {
	EXISTS("EXISTS"), SUCCESS("SUCCESS"),ERROR("ERROR");	
	
	private String mesaage;
	 
	Message(String message) {
		this.mesaage = message;
	}

	public String getMessage() {
		return mesaage;
	}
	
	public static Message parse(String val) {
		for(Message msg : Message.values()) {
			if(msg.getMessage().equals(val)) {
				return msg;
			}
		}
		return null;	
	}
}
