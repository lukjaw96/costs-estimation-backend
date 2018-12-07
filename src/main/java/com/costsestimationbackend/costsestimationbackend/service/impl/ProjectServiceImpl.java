package com.costsestimationbackend.costsestimationbackend.service.impl;

import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;
import com.costsestimationbackend.costsestimationbackend.repository.ProjectRepository;
import com.costsestimationbackend.costsestimationbackend.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project findById(int id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        return optionalProject.isPresent() ? optionalProject.get() : null;
    }

    public List<Project> findAll() {
        List<Project> list = new ArrayList<>();
        projectRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Project save(ProjectDto project) {
        Project newProject = new Project();
        newProject.setName(project.getName());
        newProject.setDescription(project.getDescription());
        newProject.setAuthor(project.getAuthor());
        newProject.setStatus(project.getStatus());
        newProject.setStartDate(project.getStartDate());
        newProject.setEndDate(project.getEndDate());
        return projectRepository.save(newProject);
    }


    @Override
    public ProjectDto update(ProjectDto projectDto) {
        Project project = findById(projectDto.getIdProject());
        if (project != null) {
            BeanUtils.copyProperties(projectDto, project, "idProject");
            projectRepository.save(project);
        }
        return projectDto;
    }

    @Override
    public void delete(int id) {
        projectRepository.deleteById(id);
    }
}
