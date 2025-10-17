package aishe.gov.in.masterseo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ref_user_management_role")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class RefUserRoleMaster {

    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

/*    @Column(name = "level")
    private Integer level;*/

    @Column(name = "level_id")
    private Integer levelId;

    @Column(name = "is_deo_applicable")
    private Boolean isDeoApplicable;
    @Column(name = "user_management_applicable")
    private Boolean userManagementApplicable;
    @Type(type = "jsonb")
    @Column(name = "level_applicable", columnDefinition = "jsonb")
    private List<Integer> levelApplicable;
    @Type(type = "jsonb")
    @JsonIgnore
    @Column(name = "subrole_level1", columnDefinition = "jsonb")
    private List<Integer> subroleLevel1;
    @Type(type = "jsonb")
    @JsonIgnore
    @Column(name = "subrole_level2", columnDefinition = "jsonb")
    private List<Integer> subroleLevel2;
    @Type(type = "jsonb")
    @JsonIgnore
    @Column(name = "subrole_level3", columnDefinition = "jsonb")
    private List<Integer> subroleLevel3;
    @Column(name = "is_state_mandatory")
    private Boolean isStateMandatory;

    @Type(type = "jsonb")
    @JsonIgnore
    @Column(name = "approving_Authority", columnDefinition = "jsonb")
    private List<Integer> approvingAuthority;

    @Column(name = "approving_authority_role_profile")
    private Boolean approvingAuthorityRole;

    @Type(type = "jsonb")
    @JsonIgnore
    @Column(name = "deo_level", columnDefinition = "jsonb")
    private List<Integer> deos;
    @Column(name = "is_sno")
    private Boolean isSno;





    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Boolean getDeoApplicable() {
        return isDeoApplicable;
    }

    public void setDeoApplicable(Boolean deoApplicable) {
        isDeoApplicable = deoApplicable;
    }

    public Boolean getUserManagementApplicable() {
        return userManagementApplicable;
    }

    public void setUserManagementApplicable(Boolean userManagementApplicable) {
        this.userManagementApplicable = userManagementApplicable;
    }

    public List<Integer> getLevelApplicable() {
        return levelApplicable;
    }

    public void setLevelApplicable(List<Integer> levelApplicable) {
        this.levelApplicable = levelApplicable;
    }

    public List<Integer> getSubroleLevel1() {
        return subroleLevel1;
    }

    public void setSubroleLevel1(List<Integer> subroleLevel1) {
        this.subroleLevel1 = subroleLevel1;
    }

    public List<Integer> getSubroleLevel2() {
        return subroleLevel2;
    }

    public void setSubroleLevel2(List<Integer> subroleLevel2) {
        this.subroleLevel2 = subroleLevel2;
    }

    public List<Integer> getSubroleLevel3() {
        return subroleLevel3;
    }

    public void setSubroleLevel3(List<Integer> subroleLevel3) {
        this.subroleLevel3 = subroleLevel3;
    }

    public Boolean getStateMandatory() {
        return isStateMandatory;
    }

    public void setStateMandatory(Boolean stateMandatory) {
        isStateMandatory = stateMandatory;
    }

    public Boolean getSno() {
        return isSno;
    }

    public void setSno(Boolean sno) {
        isSno = sno;
    }

    public List<Integer> getApprovingAuthority() {
        return approvingAuthority;
    }

    public void setApprovingAuthority(List<Integer> approvingAuthority) {
        this.approvingAuthority = approvingAuthority;
    }

    public Boolean getApprovingAuthorityRole() {
        return approvingAuthorityRole;
    }

    public void setApprovingAuthorityRole(Boolean approvingAuthorityRole) {
        this.approvingAuthorityRole = approvingAuthorityRole;
    }

    public List<Integer> getDeos() {
        return deos;
    }

    public void setDeos(List<Integer> deos) {
        this.deos = deos;
    }
}
