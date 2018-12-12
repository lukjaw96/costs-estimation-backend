package com.costsestimationbackend.costsestimationbackend.model.User;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    //@Column(nullable = false)
	@JsonIgnore
    //used to ignore sending password when getting all users
    private String password;

    @Column
    private String role;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "USER_REQUIREMENT",
//            joinColumns = @JoinColumn(name = "idUser", referencedColumnName = "idUser"),
//            inverseJoinColumns = @JoinColumn(name = "idRequirement", referencedColumnName = "idRequirement"))
//    private List<Requirement> requirements;
//
//    public List<Requirement> getRequirements() {
//        return requirements;
//    }
//
//    public void setRequirements(List<Requirement> requirements) {
//        this.requirements = requirements;
//    }

    @OneToMany(mappedBy = "user")
    private List<Estimation> estimations;


    public List<Estimation> getEstimations() {
        return estimations;
    }


    public void setEstimations(List<Estimation> estimations) {
        this.estimations = estimations;
    }

    public void addEstimations(Estimation estimation) {
        this.estimations.add(estimation);
    }
}

