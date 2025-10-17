package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "other_minority_persons_count_by_category")
public class OtherMinorityCategoryCountEO implements Serializable{

	private static final long serialVersionUID = -4029303449921550752L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="other_minority_type_id")
	private OtherMinorityTypeEO otherMinority;

	@Column(name = "general_total")
	private Integer totalGeneral;

	@Column(name = "general_females")
	private Integer totalGeneralFemale;

	@Column(name = "sc_total")
	private Integer totalScTotal;

	@Column(name = "sc_females")
	private Integer totalScFemale;

	@Column(name = "st_total")
	private Integer totalStTotal;

	@Column(name = "st_females")
	private Integer totalStFemale;

	@Column(name = "obc_total")
	private Integer totalObcTotal;

	@Column(name = "obc_females")
	private Integer totalObcFemale;

	@Column(name = "total_persons")
	private Integer totalPersons;

	@Column(name = "total_females")
	private Integer totalFemales;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OtherMinorityTypeEO getOtherMinority() {
		return otherMinority;
	}

	public void setOtherMinority(OtherMinorityTypeEO otherMinority) {
		this.otherMinority = otherMinority;
	}

	public Integer getTotalGeneral() {
		return totalGeneral;
	}

	public void setTotalGeneral(Integer totalGeneral) {
		this.totalGeneral = totalGeneral;
	}

	public Integer getTotalGeneralFemale() {
		return totalGeneralFemale;
	}

	public void setTotalGeneralFemale(Integer totalGeneralFemale) {
		this.totalGeneralFemale = totalGeneralFemale;
	}

	public Integer getTotalScTotal() {
		return totalScTotal;
	}

	public void setTotalScTotal(Integer totalScTotal) {
		this.totalScTotal = totalScTotal;
	}

	public Integer getTotalScFemale() {
		return totalScFemale;
	}

	public void setTotalScFemale(Integer totalScFemale) {
		this.totalScFemale = totalScFemale;
	}

	public Integer getTotalStTotal() {
		return totalStTotal;
	}

	public void setTotalStTotal(Integer totalStTotal) {
		this.totalStTotal = totalStTotal;
	}

	public Integer getTotalStFemale() {
		return totalStFemale;
	}

	public void setTotalStFemale(Integer totalStFemale) {
		this.totalStFemale = totalStFemale;
	}

	public Integer getTotalObcTotal() {
		return totalObcTotal;
	}

	public void setTotalObcTotal(Integer totalObcTotal) {
		this.totalObcTotal = totalObcTotal;
	}

	public Integer getTotalObcFemale() {
		return totalObcFemale;
	}

	public void setTotalObcFemale(Integer totalObcFemale) {
		this.totalObcFemale = totalObcFemale;
	}

	public Integer getTotalPersons() {
		return totalPersons;
	}

	public void setTotalPersons(Integer totalPersons) {
		this.totalPersons = totalPersons;
	}

	public Integer getTotalFemales() {
		return totalFemales;
	}

	public void setTotalFemales(Integer totalFemales) {
		this.totalFemales = totalFemales;
	}

	

}