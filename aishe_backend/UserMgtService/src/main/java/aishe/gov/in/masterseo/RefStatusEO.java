package aishe.gov.in.masterseo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_remuneration_status")
@ToString
@Getter
@Setter
public class RefStatusEO {
    @Id
    private Integer id;
    private String status;
}
