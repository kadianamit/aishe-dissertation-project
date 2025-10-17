package aishe.gov.in.masterseo;

import aishe.gov.in.utility.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@Table(name = "public.activity_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityMasterEO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "activity_id")
    private Integer activityId;
    @JsonIgnore
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @JsonIgnore
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "survey_year")
    private Integer surveyYear;
    @JsonIgnore
    @Column(name = "created_by")
    private String createdBy;
    @JsonIgnore
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @JsonIgnore
    @Column(name = "modified_by")
    private String modifiedBy;
    @JsonIgnore
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "activity_id", updatable = false, insertable = false)
    private RefActivityActionEO activity;

    private String remarks;

    @Transient
    private String startDateString;
    @Transient
    private String endDateString;

    public String getStartDateString() {
        if (null != startDate) {
            return DateUtils.convertDBDateTimeToStringNew(startDate);
        }
        return startDateString;
    }

    public String getEndDateString() {
        if (null != endDate) {
            return DateUtils.convertDBDateTimeToStringNew(endDate);
        }
        return endDateString;
    }
}
