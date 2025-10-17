package org.nicexam.authorizationservice.enums;

public enum TableNamesForIdGeneration {
	
	USERS("users"),
	USERS_CONTACTS("users_contacts");
	
	private String tableName;
	 
	TableNamesForIdGeneration(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}


	public static TableNamesForIdGeneration parse(String val) {
		for(TableNamesForIdGeneration idGeneration : TableNamesForIdGeneration.values()) {
			if(idGeneration.getTableName().equals(val)) {
				return idGeneration;
			}
		}
		return null;	
	}
}
