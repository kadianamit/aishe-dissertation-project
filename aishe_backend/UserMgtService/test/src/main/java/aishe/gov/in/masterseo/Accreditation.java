package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accreditation")
public class Accreditation {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "accreditation_body")
	private String accreditationBody;
	@Column(name = "score")
	private Double score;
	@Column(name = "max_score")
	private Integer maxScore;
	@Column(name = "has_score")
	private Boolean hasScore;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccreditationBody() {
		return accreditationBody;
	}
	public void setAccreditationBody(String accreditationBody) {
		this.accreditationBody = accreditationBody;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Integer getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}
	public Boolean getHasScore() {
		return hasScore;
	}
	public void setHasScore(Boolean hasScore) {
		this.hasScore = hasScore;
	}

	
}
