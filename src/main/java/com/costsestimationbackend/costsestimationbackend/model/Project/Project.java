package com.costsestimationbackend.costsestimationbackend.model.Project;

import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProject;

    @Column
    @NotNull
    private String name;

    @Column
    private String description;

    @Column
    private String author;

    @Column
    private String status;

    @Column
    private String startDate;

    @Column
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "REQUIREMENT_PROJECT",
            joinColumns = @JoinColumn(name = "idProject", referencedColumnName = "idProject"),
            inverseJoinColumns = @JoinColumn(name = "idRequirement", referencedColumnName = "idRequirement"))
    List<Requirement> requirements;

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

}
