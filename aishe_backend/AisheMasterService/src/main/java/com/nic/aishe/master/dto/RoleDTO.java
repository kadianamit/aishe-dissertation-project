package com.nic.aishe.master.dto;

public class RoleDTO {
    private Integer id;
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleDTO(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}
