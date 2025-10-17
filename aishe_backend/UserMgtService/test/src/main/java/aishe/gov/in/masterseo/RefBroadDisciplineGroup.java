package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ref_broad_discipline_group")
public class RefBroadDisciplineGroup {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "discipline_group")
	private String disciplineGroup;		
	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private RefBroadDisciplineGroupCategory refBroadDisciplineGroupCategory;
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public String getDisciplineGroup() {
		return disciplineGroup;
	}
	public void setDisciplineGroup(String disciplineGroup) {
		this.disciplineGroup = disciplineGroup;
	}
	public RefBroadDisciplineGroupCategory getRefBroadDisciplineGroupCategory() {
		return refBroadDisciplineGroupCategory;
	}
	public void setRefBroadDisciplineGroupCategory(RefBroadDisciplineGroupCategory refBroadDisciplineGroupCategory) {
		this.refBroadDisciplineGroupCategory = refBroadDisciplineGroupCategory;
	}
	
	
}
