package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="staff_quarter")
public class StaffQuarter {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="teaching_staff")
	private Integer teachingStaff;
	
	@Column(name="non_teaching_staff")
	private Integer nonTeachingStaff;
	
	@Column(name="total")
	private Integer total;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTeachingStaff() {
		return teachingStaff;
	}
	public void setTeachingStaff(Integer teachingStaff) {
		this.teachingStaff = teachingStaff;
	}
	public Integer getNonTeachingStaff() {
		return nonTeachingStaff;
	}
	public void setNonTeachingStaff(Integer nonTeachingStaff) {
		this.nonTeachingStaff = nonTeachingStaff;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	

}
