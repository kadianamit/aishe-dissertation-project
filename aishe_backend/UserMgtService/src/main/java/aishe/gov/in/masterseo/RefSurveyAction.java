package aishe.gov.in.masterseo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.ref_survey_acton")
@Setter
@Getter
@ToString
public class RefSurveyAction {
    @Id
    private Integer id;
    private String action;
}
