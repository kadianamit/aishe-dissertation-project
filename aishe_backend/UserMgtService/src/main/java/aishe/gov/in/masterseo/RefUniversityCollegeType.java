package aishe.gov.in.masterseo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_university_college_type")
@ToString
@Getter
@Setter
public class RefUniversityCollegeType {
    @Id
    private String id;
    private String type;
}
