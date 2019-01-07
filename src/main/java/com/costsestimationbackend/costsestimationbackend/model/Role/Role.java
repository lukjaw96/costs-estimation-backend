package com.costsestimationbackend.costsestimationbackend.model.Role;

import com.costsestimationbackend.costsestimationbackend.model.User.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;

    @Column
    private String code;

    @Column
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

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

    public Role(Integer idRole, String code, String name) {
        this.idRole = idRole;
        this.code = code;
        this.name = name;
    }

    public Role() {}

}




