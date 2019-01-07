package com.costsestimationbackend.costsestimationbackend.service;

import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.RequirementDto;

import java.util.List;

public interface ProjectService {

    Project save(ProjectDto project);

    List<ProjectDto> findAll();

    void delete(Integer id);

    ProjectDto findById(Integer id);

    ProjectDto update(ProjectDto projectDto);

    void addRequirementToProject(Integer idProject, Integer idRequirement);

    List<RequirementDto> getProjectRequirements(Integer idProject);

}
