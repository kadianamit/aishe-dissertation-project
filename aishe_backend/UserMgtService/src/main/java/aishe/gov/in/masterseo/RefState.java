package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.ref_state")
public class RefState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "st_code")
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "lgd_code")
	private Integer lgd_code;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLgd_code() {
		return lgd_code;
	}

	public void setLgd_code(Integer lgd_code) {
		this.lgd_code = lgd_code;
	}

}
