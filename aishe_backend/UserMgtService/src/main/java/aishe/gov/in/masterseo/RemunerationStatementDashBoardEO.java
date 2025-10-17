package aishe.gov.in.masterseo;

import aishe.gov.in.utility.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Entity
@Table(name = "remuneration_statement")
public class RemunerationStatementDashBoardEO {
    @Id
    private String id;
    @Column(name = "generator_user_id")
    private String generatedBy;
    @Transient
    private String generatedOn;
    @Column(name = "no_of_remunerations")
    private BigInteger no_of_remuneration;
    @Column(name = "total_amount")
    private BigInteger totalAmount;
    @JsonIgnore
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "survey_Year")
    private Integer surveyYear;

    public String getGeneratedOn() {
        if (null != timestamp)
            return DateUtils.convertDBDateTimeToString(timestamp);
        return null;
    }
}
