package com.costsestimationbackend.costsestimationbackend.model.Estimation;

import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.User.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ESTIMATION")
public class Estimation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstimation;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "id_requirement")
    private Requirement requirement;

    @Column
    @NotNull
    @Min(1)
    @Max(12)
    private Integer estimation;

    public Integer getIdEstimation() {
        return idEstimation;
    }
    public void setIdEstimation(Integer idEstimation) {
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

    public Integer getEstimation() {
        return estimation;
    }
    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }
}
