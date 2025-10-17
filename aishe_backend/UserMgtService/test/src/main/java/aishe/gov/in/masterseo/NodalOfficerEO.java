package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "public.nodal_officer")
public class NodalOfficerEO {


	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "designation")
	private String designation;
	@Column(name = "email")
	private String email;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "name")
	private String name;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "director_name")
	private String directorName;
	@Column(name = "director_telephone")
	private String directorTelephone;
	@Column(name = "director_email")
	private String directorEmail;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getDirectorTelephone() {
		return directorTelephone;
	}
	public void setDirectorTelephone(String directorTelephone) {
		this.directorTelephone = directorTelephone;
	}
	public String getDirectorEmail() {
		return directorEmail;
	}
	public void setDirectorEmail(String directorEmail) {
		this.directorEmail = directorEmail;
	}
	
	
}
