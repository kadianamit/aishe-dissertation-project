package aishe.gov.in.masterseo;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import aishe.gov.in.mastersvo.OtherAffiliatingUniversityDto;
import aishe.gov.in.mastersvo.StatuatoryBodyDto;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "institute_detail", schema = "public")
public class InstituteDetailEO {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "category_type")
	private String categoryType;

	@Column(name = "aishe_code")
	private String aisheCode;

	@Column(name = "type_id")
	private String typeId;

	@Column(name = "name")
	private String name;

	@Column(name = "management_id")
	private String managementId;

	@Column(name = "owner_ship_id")
	private String ownerShipId;

	@Column(name = "location_id")
	private String locationId;

	@Column(name = "university_id")
	private String universityId;

	@Column(name = "state_id")
	private String stateId;

	@Column(name = "district_id")
	private String districtId;
	
	@Column(name = "ulb_id")
	private Integer ulbId;
	@Column(name = "block_id")
	private Integer blockId;
	@Column(name = "address_line1")
	private String addressLine1;
	@Column(name = "address_line2")
	private String addressLine2;
	@Column(name = "city")
	private String city;
	@Column(name = "pin_code")
	private String pinCode;
	
	@Column(name = "website")
	private String website;
	
	
	
	
	@Column(name = "year_of_establishment")
	private Integer yearOfEstablishment;
	
	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "nodal_officer_name")
	private String nodalOfficerName;
	@Column(name = "nodal_officer_designation")
	private String nodalOfficerDesignation;
	@Column(name = "nodal_officer_email")
	private String nodalOfficerEmail;
	
	
	@Column(name = "nodal_officer_gender")
    private Integer nodalOfficerGender;
	@Column(name = "nodal_officer_telephone")
	private String nodalOfficerTelephone;
	@Column(name = "nodal_officer_mobile")
	private String nodalOfficerMobile;
	
	@Column(name = "head_officer_name")
	private String headOfficerName;
	@Column(name = "head_officer_designation")
	private String headOfficerDesignation;
	@Column(name = "head_officer_email")
	private String headOfficerEmail;

	@Column(name = "head_officer_mobile")
	private String headOfficerMobile;
	@Column(name = "head_officer_telephone")
	private String headOfficerTelephone;
	@Column(name = "head_officer_gender")
	private Integer headOfficerGender;
	@Column(name = "constructed_area")
	private Double constructedArea;
	@Column(name = "area")
	private Double area;
	@Column(name = "is_geospatial_data_verified")
	private Boolean isGeospatialDataVerified;
	
	@Column(name = "sub_district_id")
	private Integer subDistrictId;
	@Column(name = "is_lgd_state_verified")
	private Boolean islgdStateVerified;
	@Column(name = "is_lgd_district_verified")
	private Boolean islgdDistrictVerified;
	@Column(name = "is_lgd_sub_district_verified")
	private Boolean isLgdSubDistrictVerified;
	@Column(name = "is_bisag_verified")
	private Boolean isBisagVerified;
	
	@Column(name = "nodal_officer_title_id")
	private Integer nodalOfficerTitleId;
	@Column(name = "head_officer_title_id")
	private Integer headOfficerTitleId;
	
	@Column(name = "is_other_affiliating_university_statuatory_body")
	private Boolean isOtherAffiliatingUniversityStatuatoryBody;
	
	@Column(name = "other_affiliating_university_id")
	@Type(type = "JsonUserType")
	private List<OtherAffiliatingUniversityDto> otherAffiliatingUniversity;
	
	@Column(name = "statuatory_body_id")
	@Type(type = "JsonUserType")
	private List<StatuatoryBodyDto> statuatoryBody;
	
	@Column(name = "ministry_id")
	private Integer ministryId;
	
	@Column(name = "is_institute_detail_saved")
	@Type(type = "JsonUserType")
	private Map<String,Map<String,String>> isInstituteDetailSaved;
}
