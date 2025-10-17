package aishe.gov.in.masterseo;

import aishe.gov.in.utility.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity
@Table(name = "ciso.application_website_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CisoInfoApplicationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "application_name")
    private String applicationName;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "website_url")
    private String websiteUrl;
    private String remarks;

    @Column(name = "is_audited")
    private Boolean isAudited;

    @Column(name = "csd_report_id")
    private Integer csdReportId;
    @Column(name = "final_audit_report_id")
    private Integer finalAuditReportId;
    @Column(name = "audit_certificate_id")
    private Integer auditCertificateId;
    @Column(name = "valid_upto")
    private LocalDate validUpto;
    @Column(name = "csd_report_name")
    private String csdReportName;
    @Column(name = "final_audit_report_name")
    private String finalAuditReportName;
    @Column(name = "audit_certificate_name")
    private String auditCertificateName;
    @Transient
    private String validUptoString;
    @Transient
    private byte[] finalAuditReportDocument;
    @Transient
    private byte[] csdReportDocument;
    @Transient
    private byte[] auditCertificateDocument;

    public String getValidUptoString() {
        if (null != validUpto)
            return DateUtils.convertDBSlashDateToString(validUpto);
        return null;
    }
}
