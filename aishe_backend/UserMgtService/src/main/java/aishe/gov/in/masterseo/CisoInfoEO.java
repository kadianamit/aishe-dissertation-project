package aishe.gov.in.masterseo;

import aishe.gov.in.utility.CisoEmadedPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ciso.ciso_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CisoInfoEO {
    @JsonIgnore
    @Id
    @Embedded
    private CisoEmadedPK cisoEmadedPK;

    @Transient
    //@Column(name = "agency_code")
    @NotNull
    @NotBlank
    private String agencyCode;
    
    @Transient
    @NotNull
    @NotBlank
    private String post;
    @Column(name = "agency_name")
    //@Transient
    @NotNull
    @NotBlank
    private String agencyName;
    @Column(name = "name")
    private String name;
    private String designation;
    private String mobile;
    @Column(name = "mobile_alternate")
    private String mobileAlternate;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "email_id_alternate")
    private String emailIdAlternate;
    @Column(name = "office_address")
    private String officeAddress;
    private Integer priority;
    private String remarks;
    @Transient
    private LocalDate issuedDate;
    @Transient
    private String letterTitle;


}
