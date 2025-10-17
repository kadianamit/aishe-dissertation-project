package aishe.gov.in.mastersvo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString

public class DataSharingApiUserInformationVO {

    private Integer id;

    private String userName;

    private Integer orgCategoryId;

    private Integer ministryId;

    private String departmentName;

    private String stateCode;

    private String districtCode;

    private String nameOfAgency;

    private String nameOfNodalPerson;

    private Integer genderId;

    private String mobileNo;

    private String emailId;

    private String completeAddress;

    private String purpose;

    private List<Integer> requiredDataTypeId;

    private String requestId;



}
