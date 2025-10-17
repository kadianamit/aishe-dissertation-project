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

public class CisoEmadedPK implements Serializable {
    @Column(name = "agency_code")
    private String agencyCode;
    private String post;
}