package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RegisteredUserDTO {
    private String userId;
    private Integer roleId;
    private String instituteName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String aisheCode;
    private String stateName;
    private Integer userStatus;
    private Boolean formUpload;
    private String universityName;
    private Integer isApproved;
    private String levelName;
    private String userStatusName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String phone;
    private String mobile;
    private String emailId;
    private String stateLevelBody;
    private String stateLevelBodyInstitute;
    private String bodyType;
    private String stdCode;
    private String alternateEmailId;
    private String gender;
    private String roleName;
    private String districtName;
    private String districtCode;
    private byte[] document;
    private String documentName;
    private String stateCode;
    private String approvedBy;
    private String approvedDate;
    private String type;
}
