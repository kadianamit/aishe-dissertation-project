package aishe.gov.in.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CisoHeMdEmadedPK implements Serializable {

    private static final long serialVersionUID = -2748123577249192483L;
    @Column(name = "aishe_code")
    private String aisheCode;
    @Column(name = "institute_full_name")
    private String instituteFullName;
}