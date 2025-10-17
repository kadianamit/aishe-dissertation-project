package aishe.gov.in.masterseo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_sharing.ref_data_type_required")
@ToString
@Getter
@Setter
public class RefDataTypeRequiredEO {
    @Id
    private Integer id;
    private String type;
}
