package com.costsestimationbackend.costsestimationbackend.model.Estimation;

import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.User.User;

import javax.persistence.*;

@Entity
@Table(name = "ESTIMATION")
public class Estimation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstimation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_requirement")
    private Requirement requirement;

    private int estimation;


    public int getIdEstimation() {
        return idEstimation;
    }

    public void setIdEstimation(int idEstimation) {
        this.idEstimation = idEstimation;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }


    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }
}
