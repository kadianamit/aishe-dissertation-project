package com.nic.aishe.master.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document_upload")
public class Document implements Serializable{
	
	private static final long serialVersionUID = 8876387814582714556L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "document_name")
	private String name;

	@Column(name = "document_module")
	private String module;
	@Column(name = "last_modified_by")
	private String lastModifiedBy;

	@Column(name = "last_modified_date")
	private Date modifiedDate;

	@Column(name = "pdf_file")
	private byte[] pdf;

	@Column(name = "module_id")
	private Integer moduleId;

	@Column(name = "document_count")
	private Integer docCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public byte[] getPdfBytes() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getDocCount() {
		return docCount;
	}

	public void setDocCount(Integer docCount) {
		this.docCount = docCount;
	}

	public Document(Integer id, String name, String module, Integer moduleId) {
		super();
		this.id = id;
		this.name = name;
		this.module = module;
		this.moduleId = moduleId;
	}

	public Document() {
		super();
	}
	
	

}
