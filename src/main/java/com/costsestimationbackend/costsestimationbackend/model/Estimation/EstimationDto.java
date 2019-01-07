package com.costsestimationbackend.costsestimationbackend.model.Estimation;

public class EstimationDto {

    private Integer idEstimation;
    private Integer idUser;
    private Integer idRequirement;
    private Integer estimation;

    public Integer getIdEstimation() {
        return idEstimation;
    }
    public void setIdEstimation(Integer idEstimation) {
        this.idEstimation = idEstimation;
    }

    public Integer getIdUser() {
        return idUser;
    }
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRequirement() {
        return idRequirement;
    }
    public void setIdRequirement(Integer idRequirement) {
        this.idRequirement = idRequirement;
    }

    public Integer getEstimation() {
        return estimation;
    }
    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    public EstimationDto(Integer idEstimation,
                         Integer idUser,
                         Integer idRequirement,
                         Integer estimation) {
        this.idEstimation = idEstimation;
        this.idUser = idUser;
        this.idRequirement = idRequirement;
        this.estimation = estimation;
    }

    public EstimationDto() {}
}
