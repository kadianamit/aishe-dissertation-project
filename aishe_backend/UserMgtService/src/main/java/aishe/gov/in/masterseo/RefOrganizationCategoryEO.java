package aishe.gov.in.masterseo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_sharing.ref_organization_category")
@ToString
@Getter
@Setter
public class RefOrganizationCategoryEO {
    @Id
    private Integer id;
    private String category;
}
