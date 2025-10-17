package aishe.gov.in.masterseo;

import aishe.gov.in.security.UserInfo;
import aishe.gov.in.utility.DateUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "public.user_master_history")
public class UserMasterHistoryEO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "name")
    private String Name;

    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "address_line2")
    private String addressLine2;
    @Column(name = "city")
    private String city;
    @Column(name = "state_code")
    private String stateCode;
    @Column(name = "phone_landline")
    private String phoneLandline;
    @Column(name = "phone_mobile")
    private String phoneMobile;
    @Column(name = "email_id")
    private String emailId;

    @Column(name = "is_approved")
    private Integer isApproved;

    @Column(name = "state_level_body")
    private String stateLevelBody;
    @Column(name = "state_level_body_institute")
    private String stateLevelBodyInstitute;

    @Column(name = "address_state_code")
    private String addressStateCode;
    @Column(name = "address_district_code")
    private String addressDistrictCode;

    @Column(name = "std_code")
    private String stdCode;

    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "alt_email_id")
    private String altEmailId;

    @Column(name = "aishe_code")
    private String aisheCode;
    @Column(name = "gender")
    private Integer genderId;
    @Column(name = "body_type")
    private String bodyType;
    @Column(name = "privilege_id")
    private Integer privilegeId;

    @Column(name = "is_moe_display_user")
    private Boolean isMoeDisplayUser;

    @Column(name = "action_by")
    private String actionBy;

    @Column(name = "action_on")
    private LocalDateTime actionOn;


    public static UserMasterHistoryEO bind(UserRegistrationUpdatedDetailEO newUser, UserRegistrationUpdatedDetailEO oldUser, UserInfo userInfo) {
        UserMasterHistoryEO historyEO = new UserMasterHistoryEO();
        historyEO.setActionBy(userInfo.getUsername());
        historyEO.setActionOn(DateUtils.obtainCurrentTimeStamp());
        historyEO.setUserId(oldUser.getUserId());
        historyEO.setRoleId(oldUser.getRoleId());
        historyEO.setName(!Objects.equals(newUser.getName(), oldUser.getName()) ? oldUser.getName() : null);
      /*  historyEO.setFirstName(!Objects.equals(newUser.getFirstName(), oldUser.getFirstName()) ? oldUser.getFirstName() : null);
        historyEO.setMiddleName(!Objects.equals(newUser.getMiddleName(), oldUser.getMiddleName()) ? oldUser.getMiddleName() : null);
        historyEO.setLastName(!Objects.equals(newUser.getLastName(), oldUser.getLastName()) ? oldUser.getLastName() : null);*/
        historyEO.setAddressLine1(!Objects.equals(newUser.getAddressLine1(), oldUser.getAddressLine1()) ? oldUser.getAddressLine1() : null);
        historyEO.setAddressLine2(!Objects.equals(newUser.getAddressLine2(), oldUser.getAddressLine2()) ? oldUser.getAddressLine2() : null);
        historyEO.setCity(!Objects.equals(newUser.getCity(), oldUser.getCity()) ? oldUser.getCity() : null);
        historyEO.setStateCode(!Objects.equals(newUser.getStateCode(), oldUser.getStateCode()) ? oldUser.getStateCode() : null);
        historyEO.setPhoneLandline(!Objects.equals(newUser.getPhoneLandline(), oldUser.getPhoneLandline()) ? oldUser.getPhoneLandline() : null);
        historyEO.setPhoneMobile(!Objects.equals(newUser.getPhoneMobile(), oldUser.getPhoneMobile()) ? oldUser.getPhoneMobile() : null);
        historyEO.setEmailId(!Objects.equals(newUser.getEmailId(), oldUser.getEmailId()) ? oldUser.getEmailId() : null);
        historyEO.setIsApproved(!Objects.equals(newUser.getIsApproved(), oldUser.getIsApproved()) ? oldUser.getIsApproved() : null);
        historyEO.setStateLevelBody(!Objects.equals(newUser.getStateLevelBody(), oldUser.getStateLevelBody()) ? oldUser.getStateLevelBody() : null);
        historyEO.setStateLevelBodyInstitute(!Objects.equals(newUser.getStateLevelBodyInstitute(), oldUser.getStateLevelBodyInstitute()) ? oldUser.getStateLevelBodyInstitute() : null);
        historyEO.setAddressStateCode(!Objects.equals(newUser.getAddressStateCode(), oldUser.getAddressStateCode()) ? oldUser.getAddressStateCode() : null);
        historyEO.setAddressDistrictCode(!Objects.equals(newUser.getAddressDistrictCode(), oldUser.getAddressDistrictCode()) ? oldUser.getAddressDistrictCode() : null);
        historyEO.setStdCode(!Objects.equals(newUser.getStdCode(), oldUser.getStdCode()) ? oldUser.getStdCode() : null);
        historyEO.setIpAddress(!Objects.equals(newUser.getIpAddress(), oldUser.getIpAddress()) ? oldUser.getIpAddress() : null);
        historyEO.setStatusId(!Objects.equals(newUser.getStatusId(), oldUser.getStatusId()) ? oldUser.getStatusId() : null);
        historyEO.setAltEmailId(!Objects.equals(newUser.getAltEmailId(), oldUser.getAltEmailId()) ? oldUser.getAltEmailId() : null);
        historyEO.setAisheCode(oldUser.getAisheCode());
        historyEO.setGenderId(!Objects.equals(newUser.getGenderId(), oldUser.getGenderId()) ? oldUser.getGenderId() : null);
        historyEO.setBodyType(!Objects.equals(newUser.getBodyType(), oldUser.getBodyType()) ? oldUser.getBodyType() : null);
        historyEO.setPrivilegeId(!Objects.equals(newUser.getPrivilegeId(), oldUser.getPrivilegeId()) ? oldUser.getPrivilegeId() : null);
        return historyEO;
    }

    public static UserMasterHistoryEO bind(UserMasterNewEO newUser, UserMasterNewEO oldUser,UserInfo userInfo) {
        UserMasterHistoryEO historyEO = new UserMasterHistoryEO();
        historyEO.setActionBy(userInfo.getUsername());
        historyEO.setActionOn(DateUtils.obtainCurrentTimeStamp());
        historyEO.setUserId(oldUser.getUserId());
        historyEO.setRoleId(oldUser.getRoleId());
        historyEO.setName(!Objects.equals(newUser.getName(), oldUser.getName()) ? oldUser.getName() : null);
        historyEO.setAddressLine1(!Objects.equals(newUser.getAddressLine1(), oldUser.getAddressLine1()) ? oldUser.getAddressLine1() : null);
        historyEO.setAddressLine2(!Objects.equals(newUser.getAddressLine2(), oldUser.getAddressLine2()) ? oldUser.getAddressLine2() : null);
        historyEO.setCity(!Objects.equals(newUser.getCity(), oldUser.getCity()) ? oldUser.getCity() : null);
        historyEO.setStateCode(!Objects.equals(newUser.getStateCode(), oldUser.getStateCode()) ? oldUser.getStateCode() : null);
        historyEO.setAddressStateCode(!Objects.equals(newUser.getStateId(), oldUser.getStateId()) ? oldUser.getStateId() : null);
        historyEO.setAddressDistrictCode(!Objects.equals(newUser.getDistrictId(), oldUser.getDistrictId()) ? oldUser.getDistrictId() : null);
        historyEO.setPhoneLandline(!Objects.equals(newUser.getPhoneLandline(), oldUser.getPhoneLandline()) ? oldUser.getPhoneLandline() : null);
        historyEO.setPhoneMobile(!Objects.equals(newUser.getMobile(), oldUser.getMobile()) ? oldUser.getMobile() : null);
        historyEO.setEmailId(!Objects.equals(newUser.getEmail(), oldUser.getEmail()) ? oldUser.getEmail() : null);
        historyEO.setStdCode(!Objects.equals(newUser.getStdCode(), oldUser.getStdCode()) ? oldUser.getStdCode() : null);
        historyEO.setAisheCode(oldUser.getAisheCode());
        historyEO.setBodyType(!Objects.equals(newUser.getBodyType(), oldUser.getBodyType()) ? oldUser.getBodyType() : null);
        historyEO.setPrivilegeId(!Objects.equals(newUser.getPrivilegeId(), oldUser.getPrivilegeId()) ? oldUser.getPrivilegeId() : null);
        historyEO.setIsMoeDisplayUser(!Objects.equals(newUser.getIsMoeDisplayUser(), oldUser.getIsMoeDisplayUser()) ? oldUser.getIsMoeDisplayUser() : null);
        return historyEO;
    }

}