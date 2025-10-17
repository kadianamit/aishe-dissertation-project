package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "public.request_details")
public class RequestDetailsEO {

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "type")
	private Integer type;
	@Column(name = "document")
	private byte[] documentPdf;
	@Column(name = "created_by")
	private Integer createdBy;
	@Column(name = "address_id")
	private Integer addressId;
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	@Column(name = "ip_address")
	private String ipAddress;
	@Column(name = "is_declaration_accepted")
	private Boolean isDeclarationAccepted;
	@Column(name = "institutional_head_details")
	private Integer institutionalHeadDetails;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public byte[] getDocumentPdf() {
		return documentPdf;
	}
	public void setDocumentPdf(byte[] documentPdf) {
		this.documentPdf = documentPdf;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Boolean getIsDeclarationAccepted() {
		return isDeclarationAccepted;
	}
	public void setIsDeclarationAccepted(Boolean isDeclarationAccepted) {
		this.isDeclarationAccepted = isDeclarationAccepted;
	}
	public Integer getInstitutionalHeadDetails() {
		return institutionalHeadDetails;
	}
	public void setInstitutionalHeadDetails(Integer institutionalHeadDetails) {
		this.institutionalHeadDetails = institutionalHeadDetails;
	}
}