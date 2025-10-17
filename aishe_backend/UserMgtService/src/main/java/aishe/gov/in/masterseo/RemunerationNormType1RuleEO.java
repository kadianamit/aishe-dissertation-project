package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "public.remuneration_norm_type_1_rule")
public class RemunerationNormType1RuleEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Min(1)
    @Column(name = "survey_year")
    private Integer surveyYear;
    @NotBlank
    @NotNull
    @Column(name = "form_id")
    private String formId;
    /*@Column(name = "aishe_code")
    private String aisheCode;
    @Column(name = "institute_type")
    private String instituteType;*/
    @Column(name = "university_constituted_from_colleges")
    private Boolean universityConstitutedFromColleges;
    @Column(name = "minimum_programmes")
    private Integer minimumProgrammes;
    @Column(name = "maximum_programmes")
    private Integer maximumProgrammes;
    @Column(name = "amount")
    private BigInteger amount;

}
