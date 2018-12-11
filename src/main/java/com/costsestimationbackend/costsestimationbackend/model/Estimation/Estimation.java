package com.costsestimationbackend.costsestimationbackend.model.Estimation;

import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.User.User;

import javax.persistence.*;

@Entity
@Table(name = "ESTIMATION")
public class Estimation {
    private int idEstimation;
    private User user;
    private Requirement requirement;

    private int estimation;

    @Id
    @GeneratedValue
    public long getIdEstimation() {
        return idEstimation;
    }

    public void setIdEstimation(int idEstimation) {
        this.idEstimation = idEstimation;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_requirement")
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
