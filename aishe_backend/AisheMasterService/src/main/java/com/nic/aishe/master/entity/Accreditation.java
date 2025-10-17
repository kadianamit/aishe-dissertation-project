package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "accreditation")
public class Accreditation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "accreditation_body")
    private String accreditation;

    @Column(name = "score")
    private Double score;

    @Column(name = "max_score")
    private Double maxScore;

    @Column(name = "has_score")
    private Boolean hasScore;

    @Column(name = "accreditation_cycle_id")
    private Integer accrediationCycleId;

    @Column(name = "accreditation_status_valid")
    private Boolean accrediationStatusValid;

    @Column(name = "accreditation_validity_date")
    private Date accrediationValidityDate;

    @Column(name = "naac_grade_id")
    private Integer naacGradeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(String accreditation) {
        this.accreditation = accreditation;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Boolean getHasScore() {
        return hasScore;
    }

    public void setHasScore(Boolean hasScore) {
        this.hasScore = hasScore;
    }

    public Integer getAccrediationCycleId() {
        return accrediationCycleId;
    }

    public void setAccrediationCycleId(Integer accrediationCycleId) {
        this.accrediationCycleId = accrediationCycleId;
    }

    public Boolean getAccrediationStatusValid() {
        return accrediationStatusValid;
    }

    public void setAccrediationStatusValid(Boolean accrediationStatusValid) {
        this.accrediationStatusValid = accrediationStatusValid;
    }

    public Date getAccrediationValidityDate() {
        return accrediationValidityDate;
    }

    public void setAccrediationValidityDate(Date accrediationValidityDate) {
        this.accrediationValidityDate = accrediationValidityDate;
    }

    public Integer getNaacGradeId() {
        return naacGradeId;
    }

    public void setNaacGradeId(Integer naacGradeId) {
        this.naacGradeId = naacGradeId;
    }

    public Accreditation(Integer id, String accreditation, Double score, Double maxScore, Boolean hasScore, Integer accrediationCycleId, Boolean accrediationStatusValid, Date accrediationValidityDate, Integer naacGradeId) {
        super();
        this.id = id;
        this.accreditation = accreditation;
        this.score = score;
        this.maxScore = maxScore;
        this.hasScore = hasScore;
        this.accrediationCycleId = accrediationCycleId;
        this.accrediationStatusValid = accrediationStatusValid;
        this.accrediationValidityDate = accrediationValidityDate;
        this.naacGradeId = naacGradeId;
    }


    public Accreditation() {
        super();
    }


}
