package aishe.gov.in.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountKey implements Serializable {
    @Column(name = "user_id")
    private String userId;
    @Column(name = "survey_year")
    private Integer surveyYear;
}