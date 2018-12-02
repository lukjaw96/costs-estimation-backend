package com.costsestimationbackend.costsestimationbackend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "REQUIREMENT")
public class Requirement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    @ManyToMany
    @JoinTable(
            name="REQUIREMENT_PROJECT",
            joinColumns=@JoinColumn(name="idRequirement", referencedColumnName="idRequirement"),
            inverseJoinColumns=@JoinColumn(name="idProject", referencedColumnName="idProject"))
    private List<Project> projects;
}
