package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
//@Cacheable
//@Cacheable 
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="ref_foreign_student_type")
public class RefForeignStudentType {
	
	@Id
	private String id;

	@Column(name="name")
	private String name;
	
}
