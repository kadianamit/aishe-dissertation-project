package org.nicexam.authorizationservice.enums;

public enum UserStatus {
	NOTVERIFIED(1),VERIFIED(2),ACTIVE(3),SUSPENDED(4);
	
	private Integer userstatus;
	 
	UserStatus(Integer userstatus) {
		this.userstatus = userstatus;
	}

	public Integer getUserstatus() {
		return userstatus;
	}


	public static UserStatus parse(Integer val) {
		for(UserStatus msg : UserStatus.values()) {
			if(msg.getUserstatus().equals(val)) {
				return msg;
			}
		}
		return null;	
	}
}
