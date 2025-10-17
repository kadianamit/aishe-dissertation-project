package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.plaf.nimbus.State;
import java.time.LocalDateTime;

@Entity
@Table(name = "public.user_master")
public class UserMasterDetailWithStateEO {
    @Id
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
	@OneToOne
	@JoinColumn(name = "state_code")
	private RefState state;
    @Column(name = "is_approved")
    private Integer isApproved;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "state_level_body")
    private String stateLevelBody;
    @Column(name = "state_level_body_institute")
    private String stateLevelBodyInstitute;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "deo_user_type")
    private Integer deoUserType;
    @Column(name = "aishe_code")
    private String aisheCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RefState getState() {
        return state;
    }

    public void setState(RefState state) {
        this.state = state;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getStateLevelBody() {
        return stateLevelBody;
    }

    public void setStateLevelBody(String stateLevelBody) {
        this.stateLevelBody = stateLevelBody;
    }

    public String getStateLevelBodyInstitute() {
        return stateLevelBodyInstitute;
    }

    public void setStateLevelBodyInstitute(String stateLevelBodyInstitute) {
        this.stateLevelBodyInstitute = stateLevelBodyInstitute;
    }


    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getDeoUserType() {
        return deoUserType;
    }

    public void setDeoUserType(Integer deoUserType) {
        this.deoUserType = deoUserType;
    }

    public String getAisheCode() {
        return aisheCode;
    }

    public void setAisheCode(String aisheCode) {
        this.aisheCode = aisheCode;
    }


}