package aishe.gov.in.masterseo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "public.user_role_mapping")
public class UserRoleMappingEO {

	@Id
	@GenericGenerator(name="userrolemapping" , strategy="increment")
	@GeneratedValue(generator="userrolemapping")
	
	@Column(name = "record_id")
	private Integer recordId;
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "role_id")
	private UserRoleEO roleId;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "created_access")
	private boolean createdAccess;
	@Column(name = "read_access")
	private boolean readAccess;
	@Column(name = "update_access")
	private boolean updateAccess;
	@Column(name = "delete_access")
	private boolean deleteAccess;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_time")
	private LocalDate createdTime;
	@Column(name = "last_modified_by")
	private String lastModifiedBy;
	@Column(name = "last_modified_time")
	private LocalDate lastModifiedTime;
	@Column(name = "status")
	private String status;
	}