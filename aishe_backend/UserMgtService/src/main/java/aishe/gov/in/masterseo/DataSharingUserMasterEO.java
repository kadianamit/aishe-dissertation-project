package aishe.gov.in.masterseo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "user_master", schema = "data_sharing")
public class DataSharingUserMasterEO {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_category_id")
    private Integer userCategoryId;
    @Column(name = "user_name")
    private String userName;
    @OneToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "gender_id")
	private RefGender  gender;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "email_id")
    private String emailId;
    @OneToOne(fetch = FetchType.EAGER)
   	@NotFound(action = NotFoundAction.IGNORE)
   	@JoinColumn(name = "state_code")
   	private RefState state;
    @OneToOne(fetch = FetchType.EAGER)
   	@NotFound(action = NotFoundAction.IGNORE)
   	@JoinColumn(name = "district_code")
   	private RefDistrict district;
    @Column(name = "complete_address")
    private String completeAddress;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "password")
    private String password;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "hash_mobile")
    private String hashMobile;
    @Column(name = "hash_email")
    private String hashEmail;
    
}
