package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_broad_discipline_group_category")
public class RefBroadDisciplineGroupCategory {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "discipline_group_category")
	private String disciplineGroupCategory;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisciplineGroupCategory() {
		return disciplineGroupCategory;
	}

	public void setDisciplineGroupCategory(String disciplineGroupCategory) {
		this.disciplineGroupCategory = disciplineGroupCategory;
	}

}
