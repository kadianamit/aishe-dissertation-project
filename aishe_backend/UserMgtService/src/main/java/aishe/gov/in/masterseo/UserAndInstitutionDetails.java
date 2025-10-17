package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "user_master")
public class UserAndInstitutionDetails {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_landline")
    private String phoneLandline;
    @Column(name = "phone_mobile")
    private String phoneMobile;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "alt_email_id")
    private String altEmailId;
    @Column(name = "institution_name")
    private String institutionName;
    @Column(name = "aishe_code")
    private String aisheCode;
}
