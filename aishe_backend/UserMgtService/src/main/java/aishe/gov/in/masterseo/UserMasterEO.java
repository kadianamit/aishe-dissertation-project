package aishe.gov.in.masterseo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "public.user_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMasterEO {
    private static final long serialVersionUID = 1L;
    @Id
    @NotBlank
    @NotNull
    @Column(name = "user_id")
    private String userId;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "aishe_code")
    private String aisheCode;
   // @NotBlank
   // @NotNull
    @Column(name = "name")
    private String Name;
   // @Column(name = "first_name")//////////////first_name change
   // private String firstName;
   // @Column(name = "middle_name")
  //  private String middleName;
    //@NotBlank
   // @NotNull
  //  @Column(name = "last_name")
  //  private String lastName;
    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "address_line2")
    private String addressLine2;
    @Column(name = "city")
    private String city;
    @Column(name = "phone_landline")
    private String phoneLandline;
    @Column(name = "std_code")
    private String stdCode;
    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "address_district_code")
    private String districtId;
    @Column(name = "is_approved")
    private Integer isApproved;
    @Column(name = "address_state_code")
    private String stateId;

    // @Range(min = 10, max = 10, message = "mobile_no should be exact 10 characters.")
    @Column(name = "phone_mobile")
    private String mobile;
    @Email
    @Column(name = "email_id")
    private String email;

    @Column(name = "bcrypt_password")
    private String bcryptPassword;
    @Transient
    private String instituteName;
    @Transient
    private String stateName;
    @Transient
    private String districtName;
    @Transient
    private Integer LSY;
    @Transient
    private Integer minlsy;
    @Column(name = "state_level_body")
    private String stateLevelBody;
    @Column(name = "status_id")
    private Integer statusId;
    
    @Column(name="is_password_change")
	private Boolean isPasswordChange;
    @Column(name="password_change_date")
  	private LocalDateTime passwordChangeDate;
    @Transient
    private Integer stateLgdCode;
    @Transient
    private Integer disLgdCode;
    @Transient
    private Double lattitude;
    @Transient
    private Double longitude;
    
	/*
	 * @Column(name = "latitude") private Double latitudee;
	 * 
	 * @Column(name = "longitude") private Double longitudee;
	 * 
	 * @Column(name = "institute_head_name") private String instituteHeadName;
	 * 
	 * @Column(name = "institute_head_designation") private String
	 * instituteHeadDesignation;
	 * 
	 * @Column(name = "institute_head_email") private String instituteHeadEmail;
	 * 
	 * @Column(name = "institute_head_mobile") private String instituteHeadMobile;
	 * 
	 * @Column(name = "institute_head_telephone") private String
	 * instituteHeadTelephone;
	 */
    
    @Transient
    private boolean isFinalSubmit;
    @Transient
    private String roleName;
    
    @Transient
    private String message;
}