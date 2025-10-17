package aishe.gov.in.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemunerationKey implements Serializable {
    @Min(1)
    @Max(9999)
    @Column(name = "survey_year")
    private Integer surveyYear;
    @NotNull
    @NotBlank
    @Column(name = "form_id")
    private String formId;
}