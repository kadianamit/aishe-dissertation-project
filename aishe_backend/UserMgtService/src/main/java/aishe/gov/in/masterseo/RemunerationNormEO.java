package aishe.gov.in.masterseo;

import aishe.gov.in.utility.RemunerationKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Table(name = "public.remuneration_norm")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemunerationNormEO {
    @EmbeddedId
    @Valid
    private RemunerationKey remunerationKey;
    @Column(name = "type_id")
    private Integer typeId;
    @Column(name = "is_freezed")
    private Boolean isFreezed;
    /* @Column(name = "aishe_code")
    private String aisheCode;
    @Column(name = "institute_type")
    private String instituteType;*/
}
