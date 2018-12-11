package com.costsestimationbackend.costsestimationbackend.service.impl;

import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.RequirementDto;
import com.costsestimationbackend.costsestimationbackend.repository.ProjectRepository;
import com.costsestimationbackend.costsestimationbackend.repository.RequirementRepository;
import com.costsestimationbackend.costsestimationbackend.service.ProjectService;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RequirementRepository requirementRepository;

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

    //add requirement to project
    @Transactional
    @Override
    public void addRequirementToProject(int idProject, int idRequirement) {
        Optional<Requirement> optionalRequirement = requirementRepository.findById(idRequirement);
        Requirement requirement = optionalRequirement.isPresent() ? optionalRequirement.get() : null;
        Optional<Project> optionalProject = projectRepository.findById(idProject);
        Project project = optionalProject.isPresent() ? optionalProject.get() : null;
        project.getRequirements().add(requirement);
    }

    //get project requirements
    @Transactional
    @Override
    public List<RequirementDto> getProjectRequirements(int idProject) {
        Optional<Project> optionalProject = projectRepository.findById(idProject);
        Project project = optionalProject.isPresent() ? optionalProject.get() : null;
        Hibernate.initialize(project.getRequirements());
        List<RequirementDto> listRequirements = new ArrayList<>();
        for (Requirement req : project.getRequirements()) {
            RequirementDto newRequirement = new RequirementDto();
            newRequirement.setName(req.getName());
            newRequirement.setDescription(req.getDescription());
            newRequirement.setAuthor(req.getAuthor());
            newRequirement.setCreationDate(req.getCreationDate());
            newRequirement.setFinalCost(req.getFinalCost());
            listRequirements.add(newRequirement);
        }
        return listRequirements;
    }
}
