package com.costsestimationbackend.costsestimationbackend.service;

import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;

import java.util.List;

public interface ProjectService {

    Project save(ProjectDto project);

    List<Project> findAll();

    void delete(int id);

    Project findById(int id);

    ProjectDto update(ProjectDto projectDto);

}
