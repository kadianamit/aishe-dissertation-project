package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "public.ref_district")
public class RefDistrict implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "dist_code")
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "lgd_district_code")
	private Integer lgd_district_code;
	@ManyToOne
	@JoinColumn(name = "st_code", insertable = false, updatable = false)
	private RefState state;
	
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RefState getState() {
		return state;
	}
	public void setState(RefState state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLgd_district_code() {
		return lgd_district_code;
	}
	public void setLgd_district_code(Integer lgd_district_code) {
		this.lgd_district_code = lgd_district_code;
	}
	
	
	

}
