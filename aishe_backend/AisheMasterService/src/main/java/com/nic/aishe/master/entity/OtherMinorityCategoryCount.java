package com.nic.aishe.master.entity;

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
public class OtherMinorityCategoryCount implements Serializable{

	private static final long serialVersionUID = -4029303449921550752L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="other_minority_type_id")
	private OtherMinorityType otherMinority;

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
	
	@Column(name = "general_transgender")
	private Integer generalTransgender;
	
	@Column(name = "sc_transgender")
	private Integer scTransgender;
	
	@Column(name = "st_transgender")
	private Integer stTransgender;
	
	@Column(name = "obc_transgender")
	private Integer obcTransgender;
	
	@Column(name = "total_transgender")
	private Integer totalTransgender;
	
	@Column(name = "ews_total")
	private Integer ewsTotal;
	
	@Column(name = "ews_female")
	private Integer ewsFemale;
	
	@Column(name = "ews_transgender")
	private Integer ewsTransgender;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OtherMinorityType getOtherMinority() {
		return otherMinority;
	}

	public void setOtherMinority(OtherMinorityType otherMinority) {
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

	public Integer getGeneralTransgender() {
		return generalTransgender;
	}

	public void setGeneralTransgender(Integer generalTransgender) {
		this.generalTransgender = generalTransgender;
	}

	public Integer getScTransgender() {
		return scTransgender;
	}

	public void setScTransgender(Integer scTransgender) {
		this.scTransgender = scTransgender;
	}

	public Integer getStTransgender() {
		return stTransgender;
	}

	public void setStTransgender(Integer stTransgender) {
		this.stTransgender = stTransgender;
	}

	public Integer getObcTransgender() {
		return obcTransgender;
	}

	public void setObcTransgender(Integer obcTransgender) {
		this.obcTransgender = obcTransgender;
	}

	public Integer getTotalTransgender() {
		return totalTransgender;
	}

	public void setTotalTransgender(Integer totalTransgender) {
		this.totalTransgender = totalTransgender;
	}

	public Integer getEwsTotal() {
		return ewsTotal;
	}

	public void setEwsTotal(Integer ewsTotal) {
		this.ewsTotal = ewsTotal;
	}

	public Integer getEwsFemale() {
		return ewsFemale;
	}

	public void setEwsFemale(Integer ewsFemale) {
		this.ewsFemale = ewsFemale;
	}

	public Integer getEwsTransgender() {
		return ewsTransgender;
	}

	public void setEwsTransgender(Integer ewsTransgender) {
		this.ewsTransgender = ewsTransgender;
	}

	

}