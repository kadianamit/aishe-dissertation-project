package aishe.gov.in.masterseo;

import aishe.gov.in.utility.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;

@Entity
@Table(name = "remuneration_statement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemunerationStatementEO {
    @Id
    private String id;
    @Column(name = "generator_user_id")
    private String approverUserId;
    @Column(name = "survey_year")
    private Integer surveyYear;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "ip_address")
    private String ipAddress;
    @Transient
    private String creationDateTime;

    public String getCreationDateTime() {
        if (null != timestamp)
            return DateUtils.convertDBDateTimeToString(timestamp.toLocalDateTime());
        return null;
    }
}