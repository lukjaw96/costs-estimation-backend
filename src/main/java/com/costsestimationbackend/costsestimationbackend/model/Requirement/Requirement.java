package com.costsestimationbackend.costsestimationbackend.model.Requirement;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Project.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REQUIREMENT")
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRequirement;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String author;

    @Column
    private String creationDate;

    @Column
    private String finalCost;

    public int getIdRequirement() {
        return idRequirement;
    }

    public void setIdRequirement(int idRequirement) {
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

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "requirements")
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @OneToMany(mappedBy = "requirement")
    private List<Estimation> estimations;


    public List<Estimation> getEstimations() {
        return estimations;
    }




    public void setEstimations(List<Estimation> estimations) {
        this.estimations = estimations;
    }

    public void addEstimation(Estimation estimation) {
        this.estimations.add(estimation);
    }
}


