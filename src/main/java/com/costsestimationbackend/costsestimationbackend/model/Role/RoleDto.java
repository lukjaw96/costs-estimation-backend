package com.costsestimationbackend.costsestimationbackend.model.Role;

public class RoleDto {

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleDto(Integer idRole, String code, String name) {
        this.idRole = idRole;
        this.code = code;
        this.name = name;
    }

    private Integer idRole;
    private String code;
    private String name;

    public RoleDto() {}

}
