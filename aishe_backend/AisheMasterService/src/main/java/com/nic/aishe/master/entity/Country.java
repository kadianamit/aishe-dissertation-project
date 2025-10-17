package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "ref_country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer id;

    @Column(name = "country_name")
    private String name;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "isd_code")
    private String isdCode;
    @Transient
    List<ForeignInstituteNew> foreignInstitutes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getIsdCode() {
        return isdCode;
    }

    public void setIsdCode(String isdCode) {
        this.isdCode = isdCode;
    }


	public List<ForeignInstituteNew> getForeignInstitutes() {
		return foreignInstitutes;
	}

	public void setForeignInstitutes(List<ForeignInstituteNew> foreignInstitutes) {
		this.foreignInstitutes = foreignInstitutes;
	}
}
