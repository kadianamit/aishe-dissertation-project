package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scholarship")
public class Scholarship {
	@Id
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "count_by_category_id" ,insertable=false, updatable=false)
	private PersonsCountByCategory countByCategory;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PersonsCountByCategory getCountByCategory() {
		return countByCategory;
	}
	public void setCountByCategory(PersonsCountByCategory countByCategory) {
		this.countByCategory = countByCategory;
	}
	
	

}
