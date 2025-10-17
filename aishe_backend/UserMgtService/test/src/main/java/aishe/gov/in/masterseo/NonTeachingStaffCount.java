package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "non_teaching_staff_count")
public class NonTeachingStaffCount implements Serializable {

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "sanctioned_strength")
	private Integer sanctionedStrength;
	@ManyToOne
	@JoinColumn(name = "staff_type_id", insertable = false, updatable = false)
	private RefNonTeachingStaffType staffType;
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private RefNonTeachingStaffGroup group;	
	@ManyToOne
	@JoinColumn(name = "count_by_category_id", insertable = false, updatable = false)
	private PersonsCountByCategory countByCategory;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public RefNonTeachingStaffGroup getGroup() {
		return group;
	}
	public void setGroup(RefNonTeachingStaffGroup group) {
		this.group = group;
	}
	public Integer getSanctionedStrength() {
		return sanctionedStrength;
	}
	public void setSanctionedStrength(Integer sanctionedStrength) {
		this.sanctionedStrength = sanctionedStrength;
	}
	public PersonsCountByCategory getCountByCategory() {
		return countByCategory;
	}
	public void setCountByCategory(PersonsCountByCategory countByCategory) {
		this.countByCategory = countByCategory;
	}
	public RefNonTeachingStaffType getStaffType() {
		return staffType;
	}
	public void setStaffType(RefNonTeachingStaffType staffType) {
		this.staffType = staffType;
	}
	
	
}
