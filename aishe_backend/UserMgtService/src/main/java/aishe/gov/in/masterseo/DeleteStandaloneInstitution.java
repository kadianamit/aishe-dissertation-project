package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "deleted_standalone_institution", schema = "public")
public class DeleteStandaloneInstitution {
	
	@EmbeddedId
	private StandAloneEmadedPK universityPk;

	@Column(name = "statecode")
	private String stateCode;

	@Column(name = "name")
	private String name;

	@Column(name = "district_code")
	private String districtCode;

	@Column(name = "statebodyid")
	private Integer stateBodyId;

	@Column(name = "reason_id")
	private Integer reasonId;
	

}
