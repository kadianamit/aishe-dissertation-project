package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_programme_statutory_body")
public class ProgramStatutoryBody {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name="statutory_body")
	private String statutoryBody;
	
	@Column(name="valid_for_form2")
	private Boolean validform2;
	
	@Column(name="valid_for_form3")
	private Boolean validform3;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatutoryBody() {
		return statutoryBody;
	}
	public void setStatutoryBody(String statutoryBody) {
		this.statutoryBody = statutoryBody;
	}
	public Boolean getValidform2() {
		return validform2;
	}
	public void setValidform2(Boolean validform2) {
		this.validform2 = validform2;
	}
	public Boolean getValidform3() {
		return validform3;
	}
	public void setValidform3(Boolean validform3) {
		this.validform3 = validform3;
	}
	
	
}
