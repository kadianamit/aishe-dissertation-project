package com.nic.aishe.master.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the foreign_institute database table.
 */
@Entity
@Table(name = "foreign_institute")
public class ForeignInstitute implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;
    /*
        @Column(name="alternate_names")
        private List<String> alternateNames;
    */
    @Column(name = "country_id")
    private String countryId;

    @Column(name = "created_on")
    private Timestamp createdOn;

//	private List<String> econtacts;

    private String locality;

    private String name;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "premise_number")
    private String premiseNumber;

    @Column(name = "record_status_id")
    private String recordStatusId;

    @Column(name = "sub_locality")
    private String subLocality;

    @Column(name = "user_id")
    private String userId;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(CascadeType.REFRESH)
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;

    @Column(name = "email_id")
    private String emailId;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "telephone")
    private String telephone;

    @Column(name = "website")
    private String website;
    
    //@Column(name = "status_id")
    //private Integer statusId;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(CascadeType.REFRESH)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private RefForeignInstituteStatusEO statusId;

	/*@Column(name="vernacular_names")
	private List<String> vernacularNames;*/

    public ForeignInstitute() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }


    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Timestamp getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }


    public String getLocality() {
        return this.locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinCode() {
        return this.pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPremiseNumber() {
        return this.premiseNumber;
    }

    public void setPremiseNumber(String premiseNumber) {
        this.premiseNumber = premiseNumber;
    }

    public String getSubLocality() {
        return this.subLocality;
    }

    public void setSubLocality(String subLocality) {
        this.subLocality = subLocality;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getRecordStatusId() {
		return recordStatusId;
	}

	public void setRecordStatusId(String recordStatusId) {
		this.recordStatusId = recordStatusId;
	}

	public RefForeignInstituteStatusEO getStatusId() {
		return statusId;
	}

	public void setStatusId(RefForeignInstituteStatusEO statusId) {
		this.statusId = statusId;
	}

	
}