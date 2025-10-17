package org.nicexam.authorizationservice.usereo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.table_id_generation_counter")
public class TableIdGenerationCounterEO implements Serializable {


	private static final long serialVersionUID = -4898330668170468956L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "table_name")
	private String tableName;
	
	@Column(name = "id_counter")
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