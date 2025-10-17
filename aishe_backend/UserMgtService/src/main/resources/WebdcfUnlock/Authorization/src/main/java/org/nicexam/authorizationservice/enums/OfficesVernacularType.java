package org.nicexam.authorizationservice.enums;

public enum OfficesVernacularType {
	VERNACULAR_NAME(1),VERNACULAR_SHORT_NAME(4),VERNACULAR_ACRONYM(2);
	
	private Integer vernacularType;
	 
	OfficesVernacularType(Integer vernacularType) {
		this.vernacularType = vernacularType;
	}
	
	public Integer getVernacularType() {
		return vernacularType;
	}

	public static OfficesVernacularType parse(Integer val) {
		for(OfficesVernacularType msg : OfficesVernacularType.values()) {
			if(msg.getVernacularType().equals(val)) {
				return msg;
			}
		}
		return null;	
	}
}
