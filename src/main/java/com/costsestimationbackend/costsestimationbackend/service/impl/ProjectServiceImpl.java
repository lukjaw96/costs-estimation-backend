package com.costsestimationbackend.costsestimationbackend.service.impl;

import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.RequirementDto;
import com.costsestimationbackend.costsestimationbackend.repository.ProjectRepository;
import com.costsestimationbackend.costsestimationbackend.repository.RequirementRepository;
import com.costsestimationbackend.costsestimationbackend.service.ProjectService;
import com.costsestimationbackend.costsestimationbackend.validator.TextValidator;
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
    public ProjectDto findById(Integer id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        Project newProject = optionalProject.isPresent() ? optionalProject.get() : null;
        return new ProjectDto(
                newProject.getIdProject(),
                newProject.getName(),
                newProject.getAuthor(),
                newProject.getDescription(),
                newProject.getStatus(),
                newProject.getStartDate(),
                newProject.getEndDate()
        );
    }

    public List<ProjectDto> findAll() {
        List<Project> list = new ArrayList<>();
        projectRepository.findAll().iterator().forEachRemaining(list::add);
        List<ProjectDto> listProjectsDto = new ArrayList<>();
        for (Project pro : list) {
            ProjectDto newProject = new ProjectDto(
                    pro.getIdProject(),
                    pro.getName(),
                    pro.getDescription(),
                    pro.getAuthor(),
                    pro.getStatus(),
                    pro.getStartDate(),
                    pro.getEndDate()
            );
            listProjectsDto.add(newProject);
        }
        return listProjectsDto;
    }

    @Override
    public Project save(ProjectDto project) {
        if(project.getName() != null){
            TextValidator textValidator = new TextValidator();
            boolean valid = textValidator.validate(project.getName());
            if (valid) {
                if (projectRepository.findByName(project.getName()) == null) {
                    Project newProject = new Project();
                    newProject.setIdProject(project.getIdProject());
                    newProject.setName(project.getName());
                    newProject.setDescription(project.getDescription());
                    newProject.setAuthor(project.getAuthor());
                    newProject.setStatus(project.getStatus());
                    newProject.setStartDate(project.getStartDate());
                    newProject.setEndDate(project.getEndDate());
                    return projectRepository.save(newProject);
                }
            }
        }
        return null;
    }


    @Override
    public ProjectDto update(ProjectDto projectDto) {
        Optional<Project> optionalProject = projectRepository.findById(projectDto.getIdProject());
        Project project = optionalProject.isPresent() ? optionalProject.get() : null;
        if (project != null) {
            BeanUtils.copyProperties(projectDto, project, "idProject");
            projectRepository.save(project);
        }
        return projectDto;
    }

    @Override
    public void delete(Integer id) {
        projectRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void addRequirementToProject(Integer idProject, Integer idRequirement) {
        Optional<Requirement> optionalRequirement = requirementRepository.findById(idRequirement);
        Requirement requirement = optionalRequirement.isPresent() ? optionalRequirement.get() : null;
        Optional<Project> optionalProject = projectRepository.findById(idProject);
        Project project = optionalProject.isPresent() ? optionalProject.get() : null;
        project.getRequirements().add(requirement);
    }

    @Transactional
    @Override
    public List<RequirementDto> getProjectRequirements(Integer idProject) {
        Optional<Project> optionalProject = projectRepository.findById(idProject);
        Project project = optionalProject.isPresent() ? optionalProject.get() : null;
        Hibernate.initialize(project.getRequirements());
        List<RequirementDto> listRequirements = new ArrayList<>();
        for (Requirement req : project.getRequirements()) {
            RequirementDto newRequirement = new RequirementDto(
                    req.getIdRequirement(),
                    req.getName(),
                    req.getDescription(),
                    req.getAuthor(),
                    req.getCreationDate(),
                    req.getFinalCost()
            );
            listRequirements.add(newRequirement);
        }
        return listRequirements;
    }
}
