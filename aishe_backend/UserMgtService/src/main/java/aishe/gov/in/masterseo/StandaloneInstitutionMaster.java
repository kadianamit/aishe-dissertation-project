package aishe.gov.in.masterseo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ref_standalone_institution")
public class StandaloneInstitutionMaster implements Serializable {

    private static final long serialVersionUID = 6732141415304179701L;

   /* @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "survey_year")
    private Integer surveyYear;*/
   @EmbeddedId
   private StandAloneEmadedPK collegePk;
   @Column(name = "statecode")
   private String statecode;
   @Column(name = "name")
   private String name;
   @Column(name = "statebodyid")
   private Integer statebodyid;
   @Column(name = "district_code")
   private String districtCode;
   @Column(name = "user_by")
   private String userBy;
   @Column(name = "special_permission")
   private Boolean specialPermission;
   @Column(name = "permission_on_date")
   private LocalDateTime permissionOnDate;
   /* @Column(name = "statecode")
    private String addressLine1;
    @Column(name = "address_line2")
    private String addressLine2;
    @Column(name = "year_of_affiliation")
    private Integer yearAffiliation;
    @Column(name = "area")
    private Double area;
    @Column(name = "autonomous")
    private boolean autonomous;
    @Column(name = "block_city_town")
    private String blockCityTown;
    @Column(name = "city")
    private String city;*/
   // @Column(name = "university_id")
  //  private String universityId;

    public StandAloneEmadedPK getCollegePk() {
        return collegePk;
    }

    public void setCollegePk(StandAloneEmadedPK collegePk) {
        this.collegePk = collegePk;
    }
/*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
    }*/

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatebodyid() {
		return statebodyid;
	}

	public void setStatebodyid(Integer statebodyid) {
		this.statebodyid = statebodyid;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getUserBy() {
		return userBy;
	}

	public void setUserBy(String userBy) {
		this.userBy = userBy;
	}

	public Boolean getSpecialPermission() {
		return specialPermission;
	}

	public void setSpecialPermission(Boolean specialPermission) {
		this.specialPermission = specialPermission;
	}

	public LocalDateTime getPermissionOnDate() {
		return permissionOnDate;
	}

	public void setPermissionOnDate(LocalDateTime permissionOnDate) {
		this.permissionOnDate = permissionOnDate;
	}

    /*public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public Integer getYearAffiliation() {
        return yearAffiliation;
    }

    public void setYearAffiliation(Integer yearAffiliation) {
        this.yearAffiliation = yearAffiliation;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public boolean isAutonomous() {
        return autonomous;
    }

    public void setAutonomous(boolean autonomous) {
        this.autonomous = autonomous;
    }

    public String getBlockCityTown() {
        return blockCityTown;
    }

    public void setBlockCityTown(String blockCityTown) {
        this.blockCityTown = blockCityTown;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }*/
    
    
}
