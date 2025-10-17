package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Setter
@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Entity
@Table(name = "remuneration")
public class RemunerationEO {
    @Id
    @Column(name = "form_upload_id")
    private Integer formUploadId;
    @Column(name = "type_id")
    private Integer typeId;
    @Column(name = "norm_type_id")
    private Integer normTypeId;
    @Column(name = "amount")
    private BigInteger amount;
}
