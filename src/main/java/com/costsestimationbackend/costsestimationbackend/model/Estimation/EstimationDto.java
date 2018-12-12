package com.costsestimationbackend.costsestimationbackend.model.Estimation;

public class EstimationDto {

    private int idEstimation;
    private int idUser;
    private int idRequirement;
    private int estimation;

    public int getIdEstimation() {
        return idEstimation;
    }

    public void setIdEstimation(int idEstimation) {
        this.idEstimation = idEstimation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRequirement() {
        return idRequirement;
    }

    public void setIdRequirement(int idRequirement) {
        this.idRequirement = idRequirement;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public EstimationDto(int idEstimation,
                         int idUser,
                         int idRequirement,
                         int estimation) {
        this.idEstimation = idEstimation;
        this.idUser = idUser;
        this.idRequirement = idRequirement;
        this.estimation = estimation;
    }

    public EstimationDto() {}
}
