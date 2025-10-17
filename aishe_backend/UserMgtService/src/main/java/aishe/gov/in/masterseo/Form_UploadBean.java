package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="form_upload")
public class Form_UploadBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1470933774476259836L;
	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="form_id")
	private String form_id;
	@Column(name="university_id")
	private String university_id;
	@Column(name="standalone_institution_id")
	private Integer standalone_institution_id;
	@Column(name="college_institution_id")
	private Integer college_institution_id;
	@Column(name="uploader_id")
	private String uploader_id;
	@Column(name="upload_date")
	private Date upload_date;
	@Column(name="approver_id")
	private String approver_id;
	@Column(name="approve_date")
	private Date approve_date;
	@Column(name="pdf_form")
	private byte[] pdf_form;
	@Column(name="survey_year")
	private Integer survey_year;
	@Column(name="form_version")
	private String form_version;
	@Column(name="is_archived")
	private boolean is_archived;
	@Column(name="institute_type")
	private String institute_type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}
	public String getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(String university_id) {
		this.university_id = university_id;
	}
	public Integer getStandalone_institution_id() {
		return standalone_institution_id;
	}
	public void setStandalone_institution_id(Integer standalone_institution_id) {
		this.standalone_institution_id = standalone_institution_id;
	}
	public Integer getCollege_institution_id() {
		return college_institution_id;
	}
	public void setCollege_institution_id(Integer college_institution_id) {
		this.college_institution_id = college_institution_id;
	}
	public String getUploader_id() {
		return uploader_id;
	}
	public void setUploader_id(String uploader_id) {
		this.uploader_id = uploader_id;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(String approver_id) {
		this.approver_id = approver_id;
	}
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	public byte[] getPdf_form() {
		return pdf_form;
	}
	public void setPdf_form(byte[] pdf_form) {
		this.pdf_form = pdf_form;
	}
	public Integer getSurvey_year() {
		return survey_year;
	}
	public void setSurvey_year(Integer survey_year) {
		this.survey_year = survey_year;
	}
	public String getForm_version() {
		return form_version;
	}
	public void setForm_version(String form_version) {
		this.form_version = form_version;
	}
	public boolean isIs_archived() {
		return is_archived;
	}
	public void setIs_archived(boolean is_archived) {
		this.is_archived = is_archived;
	}
	public String getInstitute_type() {
		return institute_type;
	}
	public void setInstitute_type(String institute_type) {
		this.institute_type = institute_type;
	}

}
