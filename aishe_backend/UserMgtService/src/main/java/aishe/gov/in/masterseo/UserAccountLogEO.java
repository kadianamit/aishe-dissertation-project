package aishe.gov.in.masterseo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import aishe.gov.in.utility.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "public.user_account_log")
@NoArgsConstructor
public class UserAccountLogEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JsonIgnore
    @Column(name = "user_id")
    private String userId;
    @Column(name = "aishe_code")
    private String aisheCode;
    @Column(name = "action_by")
    private String actionBy;
    @JsonIgnore
    @Column(name = "status_id")
    private Integer statusId;
    @JsonIgnore
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "survey_year")
    private Integer surveyYear;
    @JsonIgnore
    @Column(name = "action_date_Time")
    private LocalDateTime actionTime;
    @JsonIgnore
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "remarks")
    private String remarks;

    @Transient
    private String actionDateTime;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(CascadeType.REFRESH)
    @JoinColumn(name = "status_id" , insertable = false, updatable = false)
    private RefRemunerationStatus status;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(CascadeType.REFRESH)
    @JoinColumn(name = "role_id" , insertable = false, updatable = false)
    private RefUserRoleMasterNew role;

    public UserAccountLogEO(String userId, String aisheCode, String actionBy, Integer statusId, Integer roleId, Integer surveyYear, LocalDateTime actionTime, String ipAddress, String remarks) {
        this.userId = userId;
        this.aisheCode = aisheCode;
        this.actionBy = actionBy;
        this.statusId = statusId;
        this.roleId = roleId;
        this.surveyYear = surveyYear;
        this.actionTime = actionTime;
        this.ipAddress = ipAddress;
        this.remarks = remarks;
    }

    public String getActionDateTime() {
        if (null != actionTime) {
            return DateUtils.convertDBDateTimeToStringNew(actionTime);
        }
        return actionDateTime;
    }
}
