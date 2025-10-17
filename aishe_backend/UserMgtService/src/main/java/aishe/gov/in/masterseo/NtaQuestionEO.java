package aishe.gov.in.masterseo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "miscellaneous.nta_question")
public class NtaQuestionEO {
    @Id
    private Integer id;
    private String question_no;
    private String question;
}
