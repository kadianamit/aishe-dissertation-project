package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.staff_quarter")
public class StaffQuarterEO {

	@Id
	@Column(name = "id")
	Integer id;	
	@Column(name = "teaching_staff")
	Integer teachingStaff;
	@Column(name = "non_teaching_staff")
	Integer nonTeachingStaff;
	@Column(name = "total")
	Integer total;
	
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
