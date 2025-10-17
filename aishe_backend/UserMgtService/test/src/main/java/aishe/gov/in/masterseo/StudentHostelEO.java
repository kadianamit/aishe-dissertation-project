package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "public.student_hostel")
public class StudentHostelEO {
	

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "intake_capacity")
	private Integer capacity;
	@Column(name = "name")
	private String name;
	@Column(name = "students_residing")
	private Integer resideStudent;	
	@ManyToOne
	@JoinColumn(name = "type_id", insertable = false, updatable = false)
	private RefStudentHostelType hostelType;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getResideStudent() {
		return resideStudent;
	}
	public void setResideStudent(Integer resideStudent) {
		this.resideStudent = resideStudent;
	}
	public RefStudentHostelType getHostelType() {
		return hostelType;
	}
	public void setHostelType(RefStudentHostelType hostelType) {
		this.hostelType = hostelType;
	}
	
	
	
}
