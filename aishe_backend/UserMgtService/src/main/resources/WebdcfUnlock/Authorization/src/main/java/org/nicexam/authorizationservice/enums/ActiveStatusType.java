package org.nicexam.authorizationservice.enums;

public enum ActiveStatusType {
	ACTIVE(1),INACTIVE(2),PREACTIVE(3);
	
	private Integer statusType;
	 
	ActiveStatusType(Integer vernacularType) {
		this.statusType = vernacularType;
	}
	
	public Integer getStatusType() {
		return statusType;
	}

	public static ActiveStatusType parse(Integer val) {
		for(ActiveStatusType msg : ActiveStatusType.values()) {
			if(msg.getStatusType().equals(val)) {
				return msg;
			}
		}
		return null;	
	}
	
	public static ActiveStatusType convertIntegerMasterStatusToString(int intStatus) {
		switch (intStatus) {
		case 1:
			return ActiveStatusType.ACTIVE;
		case 2:
			return ActiveStatusType.INACTIVE;
		case 3:
			return ActiveStatusType.PREACTIVE;
		default:
			break;
		}
		return null;
	}
}
