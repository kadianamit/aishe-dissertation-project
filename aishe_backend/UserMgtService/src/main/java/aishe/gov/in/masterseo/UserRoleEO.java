package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "public.user_roles")
public class UserRoleEO {

	@Id
	@Column(name = "role_id")
	private Integer roleId;
	@Column(name = "role_name")
	private String roleName;
	@Column(name = "role_description")
	private String roleDescription;
	@Column(name = "status")
	private String status;
	
}