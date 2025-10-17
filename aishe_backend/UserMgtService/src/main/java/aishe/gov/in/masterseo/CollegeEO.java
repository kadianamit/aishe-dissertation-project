package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "college")
public class CollegeEO implements Serializable {

    private static final long serialVersionUID = 6732141415304179701L;

    @EmbeddedId
    private StandAloneEmadedPK universityPk;
    private String name;
    @Column(name = "university_id")
    private String universityId;
   /* @Column(name = "district_code")
    private String districtCode;
    @Column(name = "state_code")
    private String stateCode;
    @Column(name = "type_id")
    private Character typeId;*/
    /*@Column(name = "is_dcf_applicable")
    private Boolean isDcfApplicable;*/

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StandAloneEmadedPK getUniversityPk() {
        return universityPk;
    }

    public void setUniversityPk(StandAloneEmadedPK universityPk) {
        this.universityPk = universityPk;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

   /* public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Character getTypeId() {
        return typeId;
    }

    public void setTypeId(Character typeId) {
        this.typeId = typeId;
    }
*/
/*    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
    }*/

   /* public Boolean getDcfApplicable() {
        return isDcfApplicable;
    }

    public void setDcfApplicable(Boolean dcfApplicable) {
        isDcfApplicable = dcfApplicable;
    }*/
}
