package com.costsestimationbackend.costsestimationbackend.model.Project;

public class ProjectDto {

    private Integer idProject;

    private String name;

    private String description;

    private String author;

    private String status;

    private String startDate;

    private String endDate;

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ProjectDto(Integer idProject,
                      String name,
                      String description,
                      String author,
                      String status,
                      String startDate,
                      String endDate) {
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.author = author;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ProjectDto() { }
}
