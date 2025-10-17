package org.nicexam.authorizationservice.enums;

public enum ApprovedStatus {
	PENDING("pending"), APPROVED("approved"), REJECTED("rejected");

	private String mesaage;

	ApprovedStatus(String message) {
		this.mesaage = message;
	}

	public String getMessage() {
		return mesaage;
	}

	public static ApprovedStatus parse(String val) {
		for (ApprovedStatus msg : ApprovedStatus.values()) {
			if (msg.getMessage().equals(val)) {
				return msg;
			}
		}
		return null;
	}
}
