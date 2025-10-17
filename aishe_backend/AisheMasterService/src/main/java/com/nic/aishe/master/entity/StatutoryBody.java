package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_college_institution_statutory_body")
public class StatutoryBody {
    @Id
    private String id;

    @Column(name = "statutory_body")
    private String statutoryBody;

    @Column(name = "state_code")
    private String stateCode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatutoryBody() {
        return statutoryBody;
    }

    public void setStatutoryBody(String statutoryBody) {
        this.statutoryBody = statutoryBody;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

}
