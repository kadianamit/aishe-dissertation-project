package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import aishe.gov.in.utility.CisoHeMdEmadedPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "ciso.ciso_md_hei")
public class CisoHeiEO {
	//@GenericGenerator(name="cisomdhei" , strategy="increment")
	//@GeneratedValue(generator="cisomdhei")
    private Integer id;
    @Id
    @Embedded
    private CisoHeMdEmadedPK cisoEmadedPK;
    //@Column(name = "aishe_code")
    //private String aisheCode;
    @Column(name = "institute_sub_type")
    private String instituteSubType;
    @Column(name = "institute_main_type")
    private String instituteMainType;
    @Column(name = "short_name")
    private String shortName;
    //@Column(name = "institute_full_name")
    //private String instituteFullName;
    @Column(name = "address")
    private String address;
    @Column(name = "matched_status")
    private String matchedStatus;
    @Column(name = "state_code")
    private String stateCode;
    @Transient
    private String stateName;
}
