package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_form_remuneration_norm_type")
public class RefFormRemunerationNormType {
    @Id
    @Column(name="form_id")
    private String formId;
    @Column(name="remuneration_norm_type_id")
    private Integer remunerationNormTypeId;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Integer getRemunerationNormTypeId() {
        return remunerationNormTypeId;
    }

    public void setRemunerationNormTypeId(Integer remunerationNormTypeId) {
        this.remunerationNormTypeId = remunerationNormTypeId;
    }
}
