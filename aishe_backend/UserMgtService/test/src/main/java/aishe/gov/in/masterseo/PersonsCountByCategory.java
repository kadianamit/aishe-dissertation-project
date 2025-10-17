package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persons_count_by_category")
public class PersonsCountByCategory implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "total_general_total")
	private Integer totalGeneralTotal;
	@Column(name = "total_general_females")
	private Integer totalGeneralFemales;
	@Column(name = "total_sc_total")
	private Integer totalSCTotal;
	@Column(name = "total_sc_females")
	private Integer totalSCFemales;
	@Column(name = "total_st_total")
	private Integer totalSTTotal;
	@Column(name = "total_st_females")
	private Integer totalSTFemales;
	@Column(name = "total_obc_total")
	private Integer totalOBCTotal;
	@Column(name = "total_obc_females")
	private Integer totalOBCFemales;
	@Column(name = "total_total_persons")
	private Integer totalTotalPersons;
	@Column(name = "total_total_females")
	private Integer totalTotalFemales;
	@Column(name = "total_remarks_id")
	private String totalRemarksId;
	@Column(name = "pwd_general_total")
	private Integer pwdGeneralTotal;
	@Column(name = "pwd_general_females")
	private Integer pwdGeneralFemales;
	@Column(name = "pwd_sc_total")
	private Integer pwdSCTotal;
	@Column(name = "pwd_sc_females")
	private Integer pwdSCFemales;
	@Column(name = "pwd_st_total")
	private Integer pwdSTTotal;
	@Column(name = "pwd_st_females")
	private Integer pwdSTFemales;
	@Column(name = "pwd_obc_total")
	private Integer pwdOBCTotal;
	@Column(name = "pwd_obc_females")
	private Integer pwdOBCFemales;
	@Column(name = "pwd_total_persons")
	private Integer pwdTotalPersons;
	@Column(name = "pwd_total_females")
	private Integer pwdTotalFemales;
	@Column(name = "pwd_remarks_id")
	private String pwdRemarksId;
	@Column(name = "muslim_minority_general_total")
	private Integer muslimMinorityGeneralTotal;
	@Column(name = "muslim_minority_general_females")
	private Integer muslimMinorityGeneralFemales;
	@Column(name = "muslim_minority_sc_total")
	private Integer muslimMinoritySCTotal;
	@Column(name = "muslim_minority_sc_females")
	private Integer muslimMinoritySCFemales;
	@Column(name = "muslim_minority_st_total")
	private Integer muslimMinoritySTTotal;
	@Column(name = "muslim_minority_st_females")
	private Integer muslimMinoritySTFemales;
	@Column(name = "muslim_minority_obc_total")
	private Integer muslimMinorityOBCTotal;
	@Column(name = "muslim_minority_obc_females")
	private Integer muslimMinorityOBCFemales;
	@Column(name = "muslim_minority_total_persons")
	private Integer muslimMinorityTotalPersons;
	@Column(name = "muslim_minority_total_females")
	private Integer muslimMinorityTotalFemales;
	@Column(name = "muslim_minority_remarks_id")
	private String muslimMinorityRemarksId;
	@Column(name = "other_minority_general_total")
	private Integer otherMinorityGeneralTotal;
	@Column(name = "other_minority_general_females")
	private Integer otherMinorityGeneralFemales;
	@Column(name = "other_minority_sc_total")
	private Integer otherMinoritySCTotal;
	@Column(name = "other_minority_sc_females")
	private Integer otherMinoritySCFemales;
	@Column(name = "other_minority_st_total")
	private Integer otherMinoritySTTotal;
	@Column(name = "other_minority_st_females")
	private Integer otherMinoritySTFemales;
	@Column(name = "other_minority_obc_total")
	private Integer otherMinorityOBCTotal;
	@Column(name = "other_minority_obc_females")
	private Integer otherMinorityOBCFemales;
	@Column(name = "other_minority_total_persons")
	private Integer otherMinorityTotalPersons;
	@Column(name = "other_minority_total_females")
	private Integer otherMinorityTotalFemales;
	@Column(name = "other_minority_remarks_id")
	private String otherMinorityRemarksId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTotalGeneralTotal() {
		return totalGeneralTotal;
	}
	public void setTotalGeneralTotal(Integer totalGeneralTotal) {
		this.totalGeneralTotal = totalGeneralTotal;
	}
	public Integer getTotalGeneralFemales() {
		return totalGeneralFemales;
	}
	public void setTotalGeneralFemales(Integer totalGeneralFemales) {
		this.totalGeneralFemales = totalGeneralFemales;
	}
	public Integer getTotalSCTotal() {
		return totalSCTotal;
	}
	public void setTotalSCTotal(Integer totalSCTotal) {
		this.totalSCTotal = totalSCTotal;
	}
	public Integer getTotalSCFemales() {
		return totalSCFemales;
	}
	public void setTotalSCFemales(Integer totalSCFemales) {
		this.totalSCFemales = totalSCFemales;
	}
	public Integer getTotalSTTotal() {
		return totalSTTotal;
	}
	public void setTotalSTTotal(Integer totalSTTotal) {
		this.totalSTTotal = totalSTTotal;
	}
	public Integer getTotalSTFemales() {
		return totalSTFemales;
	}
	public void setTotalSTFemales(Integer totalSTFemales) {
		this.totalSTFemales = totalSTFemales;
	}
	public Integer getTotalOBCTotal() {
		return totalOBCTotal;
	}
	public void setTotalOBCTotal(Integer totalOBCTotal) {
		this.totalOBCTotal = totalOBCTotal;
	}
	public Integer getTotalOBCFemales() {
		return totalOBCFemales;
	}
	public void setTotalOBCFemales(Integer totalOBCFemales) {
		this.totalOBCFemales = totalOBCFemales;
	}
	public Integer getTotalTotalPersons() {
		return totalTotalPersons;
	}
	public void setTotalTotalPersons(Integer totalTotalPersons) {
		this.totalTotalPersons = totalTotalPersons;
	}
	public Integer getTotalTotalFemales() {
		return totalTotalFemales;
	}
	public void setTotalTotalFemales(Integer totalTotalFemales) {
		this.totalTotalFemales = totalTotalFemales;
	}
	public String getTotalRemarksId() {
		return totalRemarksId;
	}
	public void setTotalRemarksId(String totalRemarksId) {
		this.totalRemarksId = totalRemarksId;
	}
	public Integer getPwdGeneralTotal() {
		return pwdGeneralTotal;
	}
	public void setPwdGeneralTotal(Integer pwdGeneralTotal) {
		this.pwdGeneralTotal = pwdGeneralTotal;
	}
	public Integer getPwdGeneralFemales() {
		return pwdGeneralFemales;
	}
	public void setPwdGeneralFemales(Integer pwdGeneralFemales) {
		this.pwdGeneralFemales = pwdGeneralFemales;
	}
	public Integer getPwdSCTotal() {
		return pwdSCTotal;
	}
	public void setPwdSCTotal(Integer pwdSCTotal) {
		this.pwdSCTotal = pwdSCTotal;
	}
	public Integer getPwdSCFemales() {
		return pwdSCFemales;
	}
	public void setPwdSCFemales(Integer pwdSCFemales) {
		this.pwdSCFemales = pwdSCFemales;
	}
	public Integer getPwdSTTotal() {
		return pwdSTTotal;
	}
	public void setPwdSTTotal(Integer pwdSTTotal) {
		this.pwdSTTotal = pwdSTTotal;
	}
	public Integer getPwdSTFemales() {
		return pwdSTFemales;
	}
	public void setPwdSTFemales(Integer pwdSTFemales) {
		this.pwdSTFemales = pwdSTFemales;
	}
	public Integer getPwdOBCTotal() {
		return pwdOBCTotal;
	}
	public void setPwdOBCTotal(Integer pwdOBCTotal) {
		this.pwdOBCTotal = pwdOBCTotal;
	}
	public Integer getPwdOBCFemales() {
		return pwdOBCFemales;
	}
	public void setPwdOBCFemales(Integer pwdOBCFemales) {
		this.pwdOBCFemales = pwdOBCFemales;
	}
	public Integer getPwdTotalPersons() {
		return pwdTotalPersons;
	}
	public void setPwdTotalPersons(Integer pwdTotalPersons) {
		this.pwdTotalPersons = pwdTotalPersons;
	}
	public Integer getPwdTotalFemales() {
		return pwdTotalFemales;
	}
	public void setPwdTotalFemales(Integer pwdTotalFemales) {
		this.pwdTotalFemales = pwdTotalFemales;
	}
	public String getPwdRemarksId() {
		return pwdRemarksId;
	}
	public void setPwdRemarksId(String pwdRemarksId) {
		this.pwdRemarksId = pwdRemarksId;
	}
	public Integer getMuslimMinorityGeneralTotal() {
		return muslimMinorityGeneralTotal;
	}
	public void setMuslimMinorityGeneralTotal(Integer muslimMinorityGeneralTotal) {
		this.muslimMinorityGeneralTotal = muslimMinorityGeneralTotal;
	}
	public Integer getMuslimMinorityGeneralFemales() {
		return muslimMinorityGeneralFemales;
	}
	public void setMuslimMinorityGeneralFemales(Integer muslimMinorityGeneralFemales) {
		this.muslimMinorityGeneralFemales = muslimMinorityGeneralFemales;
	}
	public Integer getMuslimMinoritySCTotal() {
		return muslimMinoritySCTotal;
	}
	public void setMuslimMinoritySCTotal(Integer muslimMinoritySCTotal) {
		this.muslimMinoritySCTotal = muslimMinoritySCTotal;
	}
	public Integer getMuslimMinoritySCFemales() {
		return muslimMinoritySCFemales;
	}
	public void setMuslimMinoritySCFemales(Integer muslimMinoritySCFemales) {
		this.muslimMinoritySCFemales = muslimMinoritySCFemales;
	}
	public Integer getMuslimMinoritySTTotal() {
		return muslimMinoritySTTotal;
	}
	public void setMuslimMinoritySTTotal(Integer muslimMinoritySTTotal) {
		this.muslimMinoritySTTotal = muslimMinoritySTTotal;
	}
	public Integer getMuslimMinoritySTFemales() {
		return muslimMinoritySTFemales;
	}
	public void setMuslimMinoritySTFemales(Integer muslimMinoritySTFemales) {
		this.muslimMinoritySTFemales = muslimMinoritySTFemales;
	}
	public Integer getMuslimMinorityOBCTotal() {
		return muslimMinorityOBCTotal;
	}
	public void setMuslimMinorityOBCTotal(Integer muslimMinorityOBCTotal) {
		this.muslimMinorityOBCTotal = muslimMinorityOBCTotal;
	}
	public Integer getMuslimMinorityOBCFemales() {
		return muslimMinorityOBCFemales;
	}
	public void setMuslimMinorityOBCFemales(Integer muslimMinorityOBCFemales) {
		this.muslimMinorityOBCFemales = muslimMinorityOBCFemales;
	}
	public Integer getMuslimMinorityTotalPersons() {
		return muslimMinorityTotalPersons;
	}
	public void setMuslimMinorityTotalPersons(Integer muslimMinorityTotalPersons) {
		this.muslimMinorityTotalPersons = muslimMinorityTotalPersons;
	}
	public Integer getMuslimMinorityTotalFemales() {
		return muslimMinorityTotalFemales;
	}
	public void setMuslimMinorityTotalFemales(Integer muslimMinorityTotalFemales) {
		this.muslimMinorityTotalFemales = muslimMinorityTotalFemales;
	}
	public String getMuslimMinorityRemarksId() {
		return muslimMinorityRemarksId;
	}
	public void setMuslimMinorityRemarksId(String muslimMinorityRemarksId) {
		this.muslimMinorityRemarksId = muslimMinorityRemarksId;
	}
	public Integer getOtherMinorityGeneralTotal() {
		return otherMinorityGeneralTotal;
	}
	public void setOtherMinorityGeneralTotal(Integer otherMinorityGeneralTotal) {
		this.otherMinorityGeneralTotal = otherMinorityGeneralTotal;
	}
	public Integer getOtherMinorityGeneralFemales() {
		return otherMinorityGeneralFemales;
	}
	public void setOtherMinorityGeneralFemales(Integer otherMinorityGeneralFemales) {
		this.otherMinorityGeneralFemales = otherMinorityGeneralFemales;
	}
	public Integer getOtherMinoritySCTotal() {
		return otherMinoritySCTotal;
	}
	public void setOtherMinoritySCTotal(Integer otherMinoritySCTotal) {
		this.otherMinoritySCTotal = otherMinoritySCTotal;
	}
	public Integer getOtherMinoritySCFemales() {
		return otherMinoritySCFemales;
	}
	public void setOtherMinoritySCFemales(Integer otherMinoritySCFemales) {
		this.otherMinoritySCFemales = otherMinoritySCFemales;
	}
	public Integer getOtherMinoritySTTotal() {
		return otherMinoritySTTotal;
	}
	public void setOtherMinoritySTTotal(Integer otherMinoritySTTotal) {
		this.otherMinoritySTTotal = otherMinoritySTTotal;
	}
	public Integer getOtherMinoritySTFemales() {
		return otherMinoritySTFemales;
	}
	public void setOtherMinoritySTFemales(Integer otherMinoritySTFemales) {
		this.otherMinoritySTFemales = otherMinoritySTFemales;
	}
	public Integer getOtherMinorityOBCTotal() {
		return otherMinorityOBCTotal;
	}
	public void setOtherMinorityOBCTotal(Integer otherMinorityOBCTotal) {
		this.otherMinorityOBCTotal = otherMinorityOBCTotal;
	}
	public Integer getOtherMinorityOBCFemales() {
		return otherMinorityOBCFemales;
	}
	public void setOtherMinorityOBCFemales(Integer otherMinorityOBCFemales) {
		this.otherMinorityOBCFemales = otherMinorityOBCFemales;
	}
	public Integer getOtherMinorityTotalPersons() {
		return otherMinorityTotalPersons;
	}
	public void setOtherMinorityTotalPersons(Integer otherMinorityTotalPersons) {
		this.otherMinorityTotalPersons = otherMinorityTotalPersons;
	}
	public Integer getOtherMinorityTotalFemales() {
		return otherMinorityTotalFemales;
	}
	public void setOtherMinorityTotalFemales(Integer otherMinorityTotalFemales) {
		this.otherMinorityTotalFemales = otherMinorityTotalFemales;
	}
	public String getOtherMinorityRemarksId() {
		return otherMinorityRemarksId;
	}
	public void setOtherMinorityRemarksId(String otherMinorityRemarksId) {
		this.otherMinorityRemarksId = otherMinorityRemarksId;
	}
}
