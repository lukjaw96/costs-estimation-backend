package com.costsestimationbackend.costsestimationbackend.controller;

import com.costsestimationbackend.costsestimationbackend.model.ApiResponse;
import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.User.User;
import com.costsestimationbackend.costsestimationbackend.model.User.UserDto;
import com.costsestimationbackend.costsestimationbackend.repository.ProjectRepository;
import com.costsestimationbackend.costsestimationbackend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping()
    public ApiResponse<List<Project>> getAllProjects() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Projects list fetched successfully.", projectService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable Integer id) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Project fetched successfully.", projectService.findById(id));
    }

    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    @PostMapping(path = "/add")
    public ApiResponse<Project> createProject(@RequestBody ProjectDto project) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Project saved successfully.", projectService.save(project));
    }

    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    @PutMapping(path = "/{id}")
    public ApiResponse<UserDto> update(@RequestBody ProjectDto projectDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Project updated successfully.", projectService.update(projectDto));
    }

    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        projectService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Project deleted successfully.", null);
    }

    @PreAuthorize("hasRole('ANALYST')")
    //adding requirement to specific project
    @PostMapping(path = "/{idProject}/requirements/add/{idRequirement}")
    public ApiResponse<Void> addRequirementToProject(@PathVariable Integer idProject, @PathVariable Integer idRequirement) {
        projectService.addRequirementToProject(idProject, idRequirement);
        return new ApiResponse<>(HttpStatus.OK.value(), "Requirements added successfully to project.", null);
    }

    //getting requirement of specific project
    @GetMapping(path = "/{idProject}/requirements")
    public ApiResponse<List<Requirement>> getProjectRequirements(@PathVariable Integer idProject) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Project requirements fetched successfully", projectService.getProjectRequirements(idProject));
    }
}
