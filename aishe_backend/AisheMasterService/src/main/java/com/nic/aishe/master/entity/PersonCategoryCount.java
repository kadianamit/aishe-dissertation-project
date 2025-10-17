package com.nic.aishe.master.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "persons_count_by_category")
public class PersonCategoryCount implements Serializable{

	private static final long serialVersionUID = -4029303449921550752L;

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "total_general_total")
	private Integer totalGeneral=0;

	@Column(name = "total_general_females")
	private Integer totalGeneralFemale=0;

	@Column(name = "total_sc_total")
	private Integer totalScTotal=0;

	@Column(name = "total_sc_females")
	private Integer totalScFemale=0;

	@Column(name = "total_st_total")
	private Integer totalStTotal=0;

	@Column(name = "total_st_females")
	private Integer totalStFemale=0;

	@Column(name = "total_obc_total")
	private Integer totalObcTotal=0;

	@Column(name = "total_obc_females")
	private Integer totalObcFemale=0;

	@Column(name = "total_total_persons")
	private Integer totalPersons=0;

	@Column(name = "total_total_females")
	private Integer totalFemales=0;

	@OneToOne
	@JoinColumn(name = "total_remarks_id")
	private Remarks remarks;

	@Column(name = "pwd_general_total")
	private Integer pwdGeneral=0;

	@Column(name = "pwd_general_females")
	private Integer pwdGeneralFemale=0;

	@Column(name = "pwd_sc_total")
	private Integer pwdScTotal=0;

	@Column(name = "pwd_sc_females")
	private Integer pwdScFemale=0;

	@Column(name = "pwd_st_total")
	private Integer pwdStTotal=0;

	@Column(name = "pwd_st_females")
	private Integer pwdStFemale=0;

	@Column(name = "pwd_obc_total")
	private Integer pwdObcTotal=0;

	@Column(name = "pwd_obc_females")
	private Integer pwdObcFemale=0;

	@Column(name = "pwd_total_persons")
	private Integer pwdPersons=0;

	@Column(name = "pwd_total_females")
	private Integer pwdFemales=0;

	@OneToOne
	@JoinColumn(name = "pwd_remarks_id")
	private Remarks pwdRemarks;

	@Column(name = "muslim_minority_general_total")
	private Integer muslimGeneral=0;

	@Column(name = "muslim_minority_general_females")
	private Integer muslimGeneralFemale=0;

	@Column(name = "muslim_minority_sc_total")
	private Integer muslimScTotal=0;

	@Column(name = "muslim_minority_sc_females")
	private Integer muslimScFemale=0;

	@Column(name = "muslim_minority_st_total")
	private Integer muslimStTotal=0;

	@Column(name = "muslim_minority_st_females")
	private Integer muslimStFemale=0;

	@Column(name = "muslim_minority_obc_total")
	private Integer muslimObcTotal=0;

	@Column(name = "muslim_minority_obc_females")
	private Integer muslimObcFemale=0;

	@Column(name = "muslim_minority_total_persons")
	private Integer muslimPersons=0;

	@Column(name = "muslim_minority_total_females")
	private Integer muslimFemales=0;

	@OneToOne
	@JoinColumn(name = "muslim_minority_remarks_id")
	private Remarks muslimRemarks;

	@Column(name = "other_minority_general_total")
	private Integer otherGeneral=0;

	@Column(name = "other_minority_general_females")
	private Integer otherGeneralFemale=0;

	@Column(name = "other_minority_sc_total")
	private Integer otherScTotal=0;

	@Column(name = "other_minority_sc_females")
	private Integer otherScFemale=0;

	@Column(name = "other_minority_st_total")
	private Integer otherStTotal=0;

	@Column(name = "other_minority_st_females")
	private Integer otherStFemale=0;

	@Column(name = "other_minority_obc_total")
	private Integer otherObcTotal=0;

	@Column(name = "other_minority_obc_females")
	private Integer otherObcFemale=0;

	@Column(name = "other_minority_total_persons")
	private Integer otherPersons=0;

	@Column(name = "other_minority_total_females")
	private Integer otherFemales=0;

	@OneToOne
	@JoinColumn(name = "other_minority_remarks_id")
	private Remarks otherRemarks;

	@Column(name = "muslim_minority_ews_total")
	private Integer muslimMinorityEwsTotal=0;

	@Column(name = "muslim_minority_ews_female")
	private Integer muslimMinorityEwsFemale=0;

	@Column(name = "muslim_minority_ews_transgender")
	private Integer muslimMinorityEwsTransgender=0;

	@Column(name = "muslim_minority_sc_transgender")
	private Integer muslimMinorityScTransgender=0;

	@Column(name = "muslim_minority_st_transgender")
	private Integer muslimMinorityStTransgender=0;

	@Column(name = "muslim_minority_obc_transgender")
	private Integer muslimMinorityObcTransgender=0;

	@Column(name = "muslim_minority_total_transgender")
	private Integer muslimMinorityTotalTransgender=0;

	@Column(name = "muslim_minority_general_transgender")
	private Integer muslimMinorityGeneralTransgender=0;

	@Column(name = "other_minority_ews_total")
	private Integer otherMinorityEwsTotal=0;

	@Column(name = "other_minority_ews_female")
	private Integer otherMinorityEwsFemale=0;

	@Column(name = "other_minority_ews_transgender")
	private Integer otherMinorityEwsTransgender=0;

	@Column(name = "other_minority_general_transgender")
	private Integer otherMinorityGeneralTransgender=0;

	@Column(name = "other_minority_sc_transgender")
	private Integer otherMinorityScTransgender=0;

	@Column(name = "other_minority_st_transgender")
	private Integer otherMinorityStTransgender=0;

	@Column(name = "other_minority_obc_transgender")
	private Integer otherMinorityObcTransgender=0;

	@Column(name = "other_minority_total_transgender")
	private Integer otherMinorityTotalTransgender=0;

	@Column(name = "pwd_ews_female")
	private Integer pwdEwsFemale=0;

	@Column(name = "pwd_ews_total")
	private Integer pwdEwsTotal=0;

	@Column(name = "pwd_ews_transgender")
	private Integer pwdEwsTransgender=0;

	@Column(name = "pwd_general_transgender")
	private Integer pwdGeneralTransgender=0;

	@Column(name = "pwd_sc_transgender")
	private Integer pwdScTransgender=0;

	@Column(name = "pwd_st_transgender")
	private Integer pwdStTransgender=0;

	@Column(name = "pwd_obc_transgender")
	private Integer pwdObcTransgender=0;

	@Column(name = "pwd_total_transgender")
	private Integer pwdTotalTransgender=0;

	@Column(name = "total_general_transgender")
	private Integer totalGeneralTransgender=0;

	@Column(name = "total_ews_total")
	private Integer totalEwsTotal=0;

	@Column(name = "total_ews_female")
	private Integer totalEwsFemale=0;
	@Column(name = "total_ews_transgender")
	private Integer totalEwsTransgender=0;

	@Column(name = "seats_earmarked_general")
	private Integer seats_earmarked_general=0;

	@Column(name = "seats_earmarked_sc")
	private Integer seats_earmarked_sc=0;

	@Column(name = "seats_earmarked_st")
	private Integer seats_earmarked_st=0;

	@Column(name = "seats_earmarked_ews")
	private Integer seats_earmarked_ews=0;

	@Column(name = "seats_earmarked_obc")
	private Integer seats_earmarked_obc=0;

	@Column(name = "total_sc_transgender")
	private Integer totalScTransgender=0;

	@Column(name = "seats_earmarked_toal")
	private Integer seats_earmarked_toal=0;

	@Column(name = "total_st_transgender")
	private Integer totalStTransgender=0;

	@Column(name = "total_obc_transgender")
	private Integer totalObcTransgender=0;

	@Column(name = "total_total_transgender")
	private Integer totalTotalTransgender=0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Remarks getRemarks() {
		return remarks;
	}

	public void setRemarks(Remarks remarks) {
		this.remarks = remarks;
	}

	public Integer getPwdGeneral() {
		return pwdGeneral;
	}

	public void setPwdGeneral(Integer pwdGeneral) {
		this.pwdGeneral = pwdGeneral;
	}

	public Integer getPwdGeneralFemale() {
		return pwdGeneralFemale;
	}

	public void setPwdGeneralFemale(Integer pwdGeneralFemale) {
		this.pwdGeneralFemale = pwdGeneralFemale;
	}

	public Integer getPwdScTotal() {
		return pwdScTotal;
	}

	public void setPwdScTotal(Integer pwdScTotal) {
		this.pwdScTotal = pwdScTotal;
	}

	public Integer getPwdScFemale() {
		return pwdScFemale;
	}

	public void setPwdScFemale(Integer pwdScFemale) {
		this.pwdScFemale = pwdScFemale;
	}

	public Integer getPwdStTotal() {
		return pwdStTotal;
	}

	public void setPwdStTotal(Integer pwdStTotal) {
		this.pwdStTotal = pwdStTotal;
	}

	public Integer getPwdStFemale() {
		return pwdStFemale;
	}

	public void setPwdStFemale(Integer pwdStFemale) {
		this.pwdStFemale = pwdStFemale;
	}

	public Integer getPwdObcTotal() {
		return pwdObcTotal;
	}

	public void setPwdObcTotal(Integer pwdObcTotal) {
		this.pwdObcTotal = pwdObcTotal;
	}

	public Integer getPwdObcFemale() {
		return pwdObcFemale;
	}

	public void setPwdObcFemale(Integer pwdObcFemale) {
		this.pwdObcFemale = pwdObcFemale;
	}

	public Integer getPwdPersons() {
		return pwdPersons;
	}

	public void setPwdPersons(Integer pwdPersons) {
		this.pwdPersons = pwdPersons;
	}

	public Integer getPwdFemales() {
		return pwdFemales;
	}

	public void setPwdFemales(Integer pwdFemales) {
		this.pwdFemales = pwdFemales;
	}

	public Remarks getPwdRemarks() {
		return pwdRemarks;
	}

	public void setPwdRemarks(Remarks pwdRemarks) {
		this.pwdRemarks = pwdRemarks;
	}

	public Integer getMuslimGeneral() {
		return muslimGeneral;
	}

	public void setMuslimGeneral(Integer muslimGeneral) {
		this.muslimGeneral = muslimGeneral;
	}

	public Integer getMuslimGeneralFemale() {
		return muslimGeneralFemale;
	}

	public void setMuslimGeneralFemale(Integer muslimGeneralFemale) {
		this.muslimGeneralFemale = muslimGeneralFemale;
	}

	public Integer getMuslimScTotal() {
		return muslimScTotal;
	}

	public void setMuslimScTotal(Integer muslimScTotal) {
		this.muslimScTotal = muslimScTotal;
	}

	public Integer getMuslimScFemale() {
		return muslimScFemale;
	}

	public void setMuslimScFemale(Integer muslimScFemale) {
		this.muslimScFemale = muslimScFemale;
	}

	public Integer getMuslimStTotal() {
		return muslimStTotal;
	}

	public void setMuslimStTotal(Integer muslimStTotal) {
		this.muslimStTotal = muslimStTotal;
	}

	public Integer getMuslimStFemale() {
		return muslimStFemale;
	}

	public void setMuslimStFemale(Integer muslimStFemale) {
		this.muslimStFemale = muslimStFemale;
	}

	public Integer getMuslimObcTotal() {
		return muslimObcTotal;
	}

	public void setMuslimObcTotal(Integer muslimObcTotal) {
		this.muslimObcTotal = muslimObcTotal;
	}

	public Integer getMuslimObcFemale() {
		return muslimObcFemale;
	}

	public void setMuslimObcFemale(Integer muslimObcFemale) {
		this.muslimObcFemale = muslimObcFemale;
	}

	public Integer getMuslimPersons() {
		return muslimPersons;
	}

	public void setMuslimPersons(Integer muslimPersons) {
		this.muslimPersons = muslimPersons;
	}

	public Integer getMuslimFemales() {
		return muslimFemales;
	}

	public void setMuslimFemales(Integer muslimFemales) {
		this.muslimFemales = muslimFemales;
	}

	public Remarks getMuslimRemarks() {
		return muslimRemarks;
	}

	public void setMuslimRemarks(Remarks muslimRemarks) {
		this.muslimRemarks = muslimRemarks;
	}

	public Integer getOtherGeneral() {
		return otherGeneral;
	}

	public void setOtherGeneral(Integer otherGeneral) {
		this.otherGeneral = otherGeneral;
	}

	public Integer getOtherGeneralFemale() {
		return otherGeneralFemale;
	}

	public void setOtherGeneralFemale(Integer otherGeneralFemale) {
		this.otherGeneralFemale = otherGeneralFemale;
	}

	public Integer getOtherScTotal() {
		return otherScTotal;
	}

	public void setOtherScTotal(Integer otherScTotal) {
		this.otherScTotal = otherScTotal;
	}

	public Integer getOtherScFemale() {
		return otherScFemale;
	}

	public void setOtherScFemale(Integer otherScFemale) {
		this.otherScFemale = otherScFemale;
	}

	public Integer getOtherStTotal() {
		return otherStTotal;
	}

	public void setOtherStTotal(Integer otherStTotal) {
		this.otherStTotal = otherStTotal;
	}

	public Integer getOtherStFemale() {
		return otherStFemale;
	}

	public void setOtherStFemale(Integer otherStFemale) {
		this.otherStFemale = otherStFemale;
	}

	public Integer getOtherObcTotal() {
		return otherObcTotal;
	}

	public void setOtherObcTotal(Integer otherObcTotal) {
		this.otherObcTotal = otherObcTotal;
	}

	public Integer getOtherObcFemale() {
		return otherObcFemale;
	}

	public void setOtherObcFemale(Integer otherObcFemale) {
		this.otherObcFemale = otherObcFemale;
	}

	public Integer getOtherPersons() {
		return otherPersons;
	}

	public void setOtherPersons(Integer otherPersons) {
		this.otherPersons = otherPersons;
	}

	public Integer getOtherFemales() {
		return otherFemales;
	}

	public void setOtherFemales(Integer otherFemales) {
		this.otherFemales = otherFemales;
	}

	public Remarks getOtherRemarks() {
		return otherRemarks;
	}

	public void setOtherRemarks(Remarks otherRemarks) {
		this.otherRemarks = otherRemarks;
	}

	public Integer getMuslimMinorityEwsTotal() {
		return muslimMinorityEwsTotal;
	}

	public void setMuslimMinorityEwsTotal(Integer muslimMinorityEwsTotal) {
		this.muslimMinorityEwsTotal = muslimMinorityEwsTotal;
	}

	public Integer getMuslimMinorityEwsFemale() {
		return muslimMinorityEwsFemale;
	}

	public void setMuslimMinorityEwsFemale(Integer muslimMinorityEwsFemale) {
		this.muslimMinorityEwsFemale = muslimMinorityEwsFemale;
	}

	public Integer getMuslimMinorityEwsTransgender() {
		return muslimMinorityEwsTransgender;
	}

	public void setMuslimMinorityEwsTransgender(Integer muslimMinorityEwsTransgender) {
		this.muslimMinorityEwsTransgender = muslimMinorityEwsTransgender;
	}

	public Integer getMuslimMinorityScTransgender() {
		return muslimMinorityScTransgender;
	}

	public void setMuslimMinorityScTransgender(Integer muslimMinorityScTransgender) {
		this.muslimMinorityScTransgender = muslimMinorityScTransgender;
	}

	public Integer getMuslimMinorityStTransgender() {
		return muslimMinorityStTransgender;
	}

	public void setMuslimMinorityStTransgender(Integer muslimMinorityStTransgender) {
		this.muslimMinorityStTransgender = muslimMinorityStTransgender;
	}

	public Integer getMuslimMinorityObcTransgender() {
		return muslimMinorityObcTransgender;
	}

	public void setMuslimMinorityObcTransgender(Integer muslimMinorityObcTransgender) {
		this.muslimMinorityObcTransgender = muslimMinorityObcTransgender;
	}

	public Integer getMuslimMinorityTotalTransgender() {
		return muslimMinorityTotalTransgender;
	}

	public void setMuslimMinorityTotalTransgender(Integer muslimMinorityTotalTransgender) {
		this.muslimMinorityTotalTransgender = muslimMinorityTotalTransgender;
	}

	public Integer getMuslimMinorityGeneralTransgender() {
		return muslimMinorityGeneralTransgender;
	}

	public void setMuslimMinorityGeneralTransgender(Integer muslimMinorityGeneralTransgender) {
		this.muslimMinorityGeneralTransgender = muslimMinorityGeneralTransgender;
	}

	public Integer getOtherMinorityEwsTotal() {
		return otherMinorityEwsTotal;
	}

	public void setOtherMinorityEwsTotal(Integer otherMinorityEwsTotal) {
		this.otherMinorityEwsTotal = otherMinorityEwsTotal;
	}

	public Integer getOtherMinorityEwsFemale() {
		return otherMinorityEwsFemale;
	}

	public void setOtherMinorityEwsFemale(Integer otherMinorityEwsFemale) {
		this.otherMinorityEwsFemale = otherMinorityEwsFemale;
	}

	public Integer getOtherMinorityEwsTransgender() {
		return otherMinorityEwsTransgender;
	}

	public void setOtherMinorityEwsTransgender(Integer otherMinorityEwsTransgender) {
		this.otherMinorityEwsTransgender = otherMinorityEwsTransgender;
	}

	public Integer getOtherMinorityGeneralTransgender() {
		return otherMinorityGeneralTransgender;
	}

	public void setOtherMinorityGeneralTransgender(Integer otherMinorityGeneralTransgender) {
		this.otherMinorityGeneralTransgender = otherMinorityGeneralTransgender;
	}

	public Integer getOtherMinorityScTransgender() {
		return otherMinorityScTransgender;
	}

	public void setOtherMinorityScTransgender(Integer otherMinorityScTransgender) {
		this.otherMinorityScTransgender = otherMinorityScTransgender;
	}

	public Integer getOtherMinorityStTransgender() {
		return otherMinorityStTransgender;
	}

	public void setOtherMinorityStTransgender(Integer otherMinorityStTransgender) {
		this.otherMinorityStTransgender = otherMinorityStTransgender;
	}

	public Integer getOtherMinorityObcTransgender() {
		return otherMinorityObcTransgender;
	}

	public void setOtherMinorityObcTransgender(Integer otherMinorityObcTransgender) {
		this.otherMinorityObcTransgender = otherMinorityObcTransgender;
	}

	public Integer getOtherMinorityTotalTransgender() {
		return otherMinorityTotalTransgender;
	}

	public void setOtherMinorityTotalTransgender(Integer otherMinorityTotalTransgender) {
		this.otherMinorityTotalTransgender = otherMinorityTotalTransgender;
	}

	public Integer getPwdEwsFemale() {
		return pwdEwsFemale;
	}

	public void setPwdEwsFemale(Integer pwdEwsFemale) {
		this.pwdEwsFemale = pwdEwsFemale;
	}

	public Integer getPwdEwsTotal() {
		return pwdEwsTotal;
	}

	public void setPwdEwsTotal(Integer pwdEwsTotal) {
		this.pwdEwsTotal = pwdEwsTotal;
	}

	public Integer getPwdEwsTransgender() {
		return pwdEwsTransgender;
	}

	public void setPwdEwsTransgender(Integer pwdEwsTransgender) {
		this.pwdEwsTransgender = pwdEwsTransgender;
	}

	public Integer getPwdGeneralTransgender() {
		return pwdGeneralTransgender;
	}

	public void setPwdGeneralTransgender(Integer pwdGeneralTransgender) {
		this.pwdGeneralTransgender = pwdGeneralTransgender;
	}

	public Integer getPwdScTransgender() {
		return pwdScTransgender;
	}

	public void setPwdScTransgender(Integer pwdScTransgender) {
		this.pwdScTransgender = pwdScTransgender;
	}

	public Integer getPwdStTransgender() {
		return pwdStTransgender;
	}

	public void setPwdStTransgender(Integer pwdStTransgender) {
		this.pwdStTransgender = pwdStTransgender;
	}

	public Integer getPwdObcTransgender() {
		return pwdObcTransgender;
	}

	public void setPwdObcTransgender(Integer pwdObcTransgender) {
		this.pwdObcTransgender = pwdObcTransgender;
	}

	public Integer getPwdTotalTransgender() {
		return pwdTotalTransgender;
	}

	public void setPwdTotalTransgender(Integer pwdTotalTransgender) {
		this.pwdTotalTransgender = pwdTotalTransgender;
	}

	public Integer getTotalEwsTotal() {
		return totalEwsTotal;
	}

	public void setTotalEwsTotal(Integer totalEwsTotal) {
		this.totalEwsTotal = totalEwsTotal;
	}

	public Integer getTotalEwsFemale() {
		return totalEwsFemale;
	}

	public void setTotalEwsFemale(Integer totalEwsFemale) {
		this.totalEwsFemale = totalEwsFemale;
	}

	public Integer getTotalEwsTransgender() {
		return totalEwsTransgender;
	}

	public void setTotalEwsTransgender(Integer totalEwsTransgender) {
		this.totalEwsTransgender = totalEwsTransgender;
	}

	public Integer getSeats_earmarked_general() {
		return seats_earmarked_general;
	}

	public void setSeats_earmarked_general(Integer seats_earmarked_general) {
		this.seats_earmarked_general = seats_earmarked_general;
	}

	public Integer getSeats_earmarked_sc() {
		return seats_earmarked_sc;
	}

	public void setSeats_earmarked_sc(Integer seats_earmarked_sc) {
		this.seats_earmarked_sc = seats_earmarked_sc;
	}

	public Integer getSeats_earmarked_st() {
		return seats_earmarked_st;
	}

	public void setSeats_earmarked_st(Integer seats_earmarked_st) {
		this.seats_earmarked_st = seats_earmarked_st;
	}

	public Integer getSeats_earmarked_ews() {
		return seats_earmarked_ews;
	}

	public void setSeats_earmarked_ews(Integer seats_earmarked_ews) {
		this.seats_earmarked_ews = seats_earmarked_ews;
	}

	public Integer getSeats_earmarked_obc() {
		return seats_earmarked_obc;
	}

	public void setSeats_earmarked_obc(Integer seats_earmarked_obc) {
		this.seats_earmarked_obc = seats_earmarked_obc;
	}

	public Integer getTotalScTransgender() {
		return totalScTransgender;
	}

	public void setTotalScTransgender(Integer totalScTransgender) {
		this.totalScTransgender = totalScTransgender;
	}

	public Integer getSeats_earmarked_toal() {
		return seats_earmarked_toal;
	}

	public void setSeats_earmarked_toal(Integer seats_earmarked_toal) {
		this.seats_earmarked_toal = seats_earmarked_toal;
	}

	public Integer getTotalStTransgender() {
		return totalStTransgender;
	}

	public void setTotalStTransgender(Integer totalStTransgender) {
		this.totalStTransgender = totalStTransgender;
	}

	public Integer getTotalObcTransgender() {
		return totalObcTransgender;
	}

	public void setTotalObcTransgender(Integer totalObcTransgender) {
		this.totalObcTransgender = totalObcTransgender;
	}

	public Integer getTotalTotalTransgender() {
		return totalTotalTransgender;
	}

	public void setTotalTotalTransgender(Integer totalTotalTransgender) {
		this.totalTotalTransgender = totalTotalTransgender;
	}

	public Integer getTotalGeneralTransgender() {
		return totalGeneralTransgender;
	}

	public void setTotalGeneralTransgender(Integer totalGeneralTransgender) {
		this.totalGeneralTransgender = totalGeneralTransgender;
	}

	@Override
	public String toString() {
		return "PersonCategoryCount{" +
				"id=" + id +
				", totalGeneral=" + totalGeneral +
				", totalGeneralFemale=" + totalGeneralFemale +
				", totalScTotal=" + totalScTotal +
				", totalScFemale=" + totalScFemale +
				", totalStTotal=" + totalStTotal +
				", totalStFemale=" + totalStFemale +
				", totalObcTotal=" + totalObcTotal +
				", totalObcFemale=" + totalObcFemale +
				", totalPersons=" + totalPersons +
				", totalFemales=" + totalFemales +
				", remarks=" + remarks +
				", pwdGeneral=" + pwdGeneral +
				", pwdGeneralFemale=" + pwdGeneralFemale +
				", pwdScTotal=" + pwdScTotal +
				", pwdScFemale=" + pwdScFemale +
				", pwdStTotal=" + pwdStTotal +
				", pwdStFemale=" + pwdStFemale +
				", pwdObcTotal=" + pwdObcTotal +
				", pwdObcFemale=" + pwdObcFemale +
				", pwdPersons=" + pwdPersons +
				", pwdFemales=" + pwdFemales +
				", pwdRemarks=" + pwdRemarks +
				", muslimGeneral=" + muslimGeneral +
				", muslimGeneralFemale=" + muslimGeneralFemale +
				", muslimScTotal=" + muslimScTotal +
				", muslimScFemale=" + muslimScFemale +
				", muslimStTotal=" + muslimStTotal +
				", muslimStFemale=" + muslimStFemale +
				", muslimObcTotal=" + muslimObcTotal +
				", muslimObcFemale=" + muslimObcFemale +
				", muslimPersons=" + muslimPersons +
				", muslimFemales=" + muslimFemales +
				", muslimRemarks=" + muslimRemarks +
				", otherGeneral=" + otherGeneral +
				", otherGeneralFemale=" + otherGeneralFemale +
				", otherScTotal=" + otherScTotal +
				", otherScFemale=" + otherScFemale +
				", otherStTotal=" + otherStTotal +
				", otherStFemale=" + otherStFemale +
				", otherObcTotal=" + otherObcTotal +
				", otherObcFemale=" + otherObcFemale +
				", otherPersons=" + otherPersons +
				", otherFemales=" + otherFemales +
				", otherRemarks=" + otherRemarks +
				", muslimMinorityEwsTotal=" + muslimMinorityEwsTotal +
				", muslimMinorityEwsFemale=" + muslimMinorityEwsFemale +
				", muslimMinorityEwsTransgender=" + muslimMinorityEwsTransgender +
				", muslimMinorityScTransgender=" + muslimMinorityScTransgender +
				", muslimMinorityStTransgender=" + muslimMinorityStTransgender +
				", muslimMinorityObcTransgender=" + muslimMinorityObcTransgender +
				", muslimMinorityTotalTransgender=" + muslimMinorityTotalTransgender +
				", muslimMinorityGeneralTransgender=" + muslimMinorityGeneralTransgender +
				", otherMinorityEwsTotal=" + otherMinorityEwsTotal +
				", otherMinorityEwsFemale=" + otherMinorityEwsFemale +
				", otherMinorityEwsTransgender=" + otherMinorityEwsTransgender +
				", otherMinorityGeneralTransgender=" + otherMinorityGeneralTransgender +
				", otherMinorityScTransgender=" + otherMinorityScTransgender +
				", otherMinorityStTransgender=" + otherMinorityStTransgender +
				", otherMinorityObcTransgender=" + otherMinorityObcTransgender +
				", otherMinorityTotalTransgender=" + otherMinorityTotalTransgender +
				", pwdEwsFemale=" + pwdEwsFemale +
				", pwdEwsTotal=" + pwdEwsTotal +
				", pwdEwsTransgender=" + pwdEwsTransgender +
				", pwdGeneralTransgender=" + pwdGeneralTransgender +
				", pwdScTransgender=" + pwdScTransgender +
				", pwdStTransgender=" + pwdStTransgender +
				", pwdObcTransgender=" + pwdObcTransgender +
				", pwdTotalTransgender=" + pwdTotalTransgender +
				", totalGeneralTransgender=" + totalGeneralTransgender +
				", totalEwsTotal=" + totalEwsTotal +
				", totalEwsFemale=" + totalEwsFemale +
				", totalEwsTransgender=" + totalEwsTransgender +
				", seats_earmarked_general=" + seats_earmarked_general +
				", seats_earmarked_sc=" + seats_earmarked_sc +
				", seats_earmarked_st=" + seats_earmarked_st +
				", seats_earmarked_ews=" + seats_earmarked_ews +
				", seats_earmarked_obc=" + seats_earmarked_obc +
				", totalScTransgender=" + totalScTransgender +
				", seats_earmarked_toal=" + seats_earmarked_toal +
				", totalStTransgender=" + totalStTransgender +
				", totalObcTransgender=" + totalObcTransgender +
				", totalTotalTransgender=" + totalTotalTransgender +
				'}';
	}


}