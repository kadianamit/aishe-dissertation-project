package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "college_institution")
public class CollegeInstitutionEO implements Serializable {

    private static final long serialVersionUID = 6732141415304179701L;

   /* @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "survey_year")
    private Integer surveyYear;*/
   @EmbeddedId
   private StandAloneEmadedPK collegePk;

   /* @Column(name = "address_line1")
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
    @Column(name = "university_id")
    private String universityId;

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

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }
}
