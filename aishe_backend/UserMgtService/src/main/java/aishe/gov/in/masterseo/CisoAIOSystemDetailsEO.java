package aishe.gov.in.masterseo;

import aishe.gov.in.utility.EncryptionDecryptionUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity
@Table(name = "ciso.aio_system_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CisoAIOSystemDetailsEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "designation")
    private String designation;
    @Column(name = "section")
    private String section;
    @Column(name = "room_no")
    private String roomNo;
    @Column(name = "model_no")
    private String modelNo;
    @Column(name = "processor")
    private String processor;
    @Column(name = "cpu_sr_no")
    private String cpuSrNo;
    @Column(name = "original_os")
    private String originalOs;
    @Column(name = "current_os")
    private String currentOs;
    @Column(name = "window_pirated_or_not")
    private String windowPiratedOrNot;
    @Column(name = "ms_office")
    private String msOffice;
    @Column(name = "ms_office_pirated_or_not")
    private String msOfficePiratedOrNot;
    @Column(name = "antivirus")
    private String antivirus;
    @Column(name = "intenet_oprator")
    private String intenetOprator;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "mac_address")
    private String macAddress;
    @Column(name = "system_age_approx")
    private String systemAgeApprox;
    @Column(name = "system_condition")
    private String systemCondition;
    @Column(name = "gemc_order_number")
    private String gemcOrderNumber;
    @Column(name = "gemc_order_date")
    private String gemcOrderDate;
    @Column(name = "invoice_date")
    private String invoiceDate;
    @Column(name = "warranty_start_date")
    private String warrantyStartDate;
    @Column(name = "warranty_end_date")
    private String warrantyEndDate;
    @Column(name = "configuration")
    private String configuration;
    @Column(name = "pc_age_as_on_11_oct_2023")
    private String pcAgeAsOn11Act2023;
    @Column(name = "system_type")
    private String systemType;
    @Column(name = "maintenance_type")
    private String maintenanceType;
    @Column(name = "location")
    private String location;
    @Column(name = "department")
    private String department;
    @JsonIgnore
    @Column(name = "email")
    private String emailId;
    @JsonIgnore
    @Column(name = "mobile_no")
    private String mobileNo;
    @JsonIgnore
    @Column(name = "landline")
    private String landlineNo;


    @Transient
    private String email;
    @Transient
    private String mobile;
    @Transient
    private String landline;


    public String getEmail() {
        if (null != emailId) {
            return EncryptionDecryptionUtil.getDecryptedString(getEmailId());
        }
        return email;
    }

    public String getMobile() {
        if (null != mobileNo) {
            return EncryptionDecryptionUtil.getDecryptedString(getMobileNo());
        }
        return mobile;
    }

    public String getLandline() {
        if (null != landlineNo) {
            return EncryptionDecryptionUtil.getDecryptedString(getLandlineNo());
        }
        return landline;
    }
}