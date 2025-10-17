package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;


public class TableIdGenerationCounterVO implements Serializable {

	private static final long serialVersionUID = -4959283854486554626L;

	private Long Id;
	
	private String tableName;
	
	private Long idCounter;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getIdCounter() {
		return idCounter;
	}

	public void setIdCounter(Long idCounter) {
		this.idCounter = idCounter;
	}
	
	
}