package aishe.gov.in.masterseo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.ref_university_type")
@ToString
@Getter
@Setter
public class RefUniversityType {
    @Id
    private String id;
    private String type;
}
