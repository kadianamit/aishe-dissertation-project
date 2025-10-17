package aishe.gov.in.mastersvo;

import aishe.gov.in.masterseo.RefDataTypeRequiredEO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString

public class DataSharingApiUserInformationWithStatus {

    private Integer id;

    private String userName;

    private Integer orgCategoryId;

    private String orgCategoryName;

    private Integer ministryId;

    private String ministryName;

    private String departmentName;

    private String stateCode;

    private String stateName;

    private String districtCode;

    private String districtName;

    private String nameOfAgency;

    private String nameOfNodalPerson;

    private Integer genderId;

    private String genderName;

    private String mobileNo;

    private String emailId;

    private String completeAddress;

    private String purpose;

    private List<RefDataTypeRequiredEO> requiredDataType;

    private String requestId;

    private byte[] requestLetter;

    private String requestLetterName;

    private String submittedOn;

    private Integer statusId;

    private String statusName;

    private String actionDateTime;

    private String approverId;


}
