package aishe.gov.in.masterseo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Data
@ToString
@Entity
@Table(name = "api_user_information", schema = "data_sharing")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class DataSharingApiUserInformationEO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /* @Column(name = "user_name")
     private String userName;*/
    @Column(name = "org_category_id")
    private Integer orgCategoryId;
    @Column(name = "ministry_id")
    private Integer ministryId;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "state_code")
    private String stateCode;
    /*@Column(name = "district_code")
    private String districtCode;*/
    @Column(name = "name_of_agency")
    private String nameOfAgency;
    @Column(name = "name_of_nodal_person")
    private String nameOfNodalPerson;
    @Column(name = "gender_id")
    private Integer genderId;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "complete_address")
    private String completeAddress;
    @Column(name = "purpose")
    private String purpose;

    @Type(type = "jsonb")
    @Column(name = "required_data_type_id", columnDefinition = "jsonb")
    private List<Integer> requiredDataTypeId;
    @Column(name = "request_letter")
    private byte[] requestLetter;
    @Column(name = "request_letter_name")
    private String requestLetterName;
    @Column(name = "request_id")
    private String requestId;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "submission_date")
    private LocalDateTime submissionDate;


}
