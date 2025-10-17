package org.nicexam.authorizationservice.enums;

public enum VernacularType {
	VERNACULAR_NAME(1),VERNACULAR_SHORT_NAME(2),VERNACULAR_ACRONYM(3),VERNACULAR_ALTERNATE_NAME(4);
	
	private Integer vernacularType;
	
	VernacularType(Integer vernacularType) {
		this.vernacularType = vernacularType;
	}
	
	public Integer getVernacularType() {
		return vernacularType;
	}

	public static VernacularType parse(Integer val) {
		for(VernacularType msg : VernacularType.values()) {
			if(msg.getVernacularType().equals(val)) {
				return msg;
			}
		}
		return null;	
	}
}