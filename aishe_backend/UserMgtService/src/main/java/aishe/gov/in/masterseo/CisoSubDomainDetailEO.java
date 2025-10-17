package aishe.gov.in.masterseo;

import aishe.gov.in.utility.EncryptionDecryptionUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "ciso.sub_domain_details")
public class CisoSubDomainDetailEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sub_domain_name")
    private String subDomainName;
    private String department;
    private boolean status;
    private String purpose;
}
