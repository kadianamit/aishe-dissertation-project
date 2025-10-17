package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.ref_activity")
public class RefActivityEO {

	@Id
	@Column(name = "id")
	private Integer Id;
	@Column(name = "activity_name")
	private String activityName;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	}