package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_standalone_deaffiliation_reason")
public class RefStandaloneDeaffilationReason {

    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String Name;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
}
