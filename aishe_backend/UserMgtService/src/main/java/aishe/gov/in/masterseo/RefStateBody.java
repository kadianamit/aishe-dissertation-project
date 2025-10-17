package aishe.gov.in.masterseo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_state_body")
@ToString
@Getter
@Setter
public class RefStateBody {
    @Id
    private Integer id;
    private String type;
}
