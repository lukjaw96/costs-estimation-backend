package com.costsestimationbackend.costsestimationbackend.model.Requirement;

public class RequirementDto {

    private Integer idRequirement;

    private String name;

    private String description;

    private String author;

    private String creationDate;

    private String finalCost;

    public Integer getIdRequirement() {
        return idRequirement;
    }

    public void setIdRequirement(Integer idRequirement) {
        this.idRequirement = idRequirement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(String finalCost) {
        this.finalCost = finalCost;
    }

    public RequirementDto(Integer idRequirement,
                          String name,
                          String description,
                          String author,
                          String creationDate,
                          String finalCost) {
        this.idRequirement = idRequirement;
        this.name = name;
        this.description = description;
        this.author = author;
        this.creationDate = creationDate;
        this.finalCost = finalCost;
    }

    public RequirementDto() {};
}
