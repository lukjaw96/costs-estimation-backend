package com.costsestimationbackend.costsestimationbackend.controller;

import com.costsestimationbackend.costsestimationbackend.model.ApiResponse;
import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;
import com.costsestimationbackend.costsestimationbackend.model.User.User;
import com.costsestimationbackend.costsestimationbackend.model.User.UserDto;
import com.costsestimationbackend.costsestimationbackend.model.User.UserPasswordUpdate;
import com.costsestimationbackend.costsestimationbackend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(path = "/projects")
    public ApiResponse<List<Project>> getAllProjects(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Projects list fetched successfully.", projectService.findAll());
    }

    @GetMapping("/projects/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Project fetched successfully.",projectService.findById(id));
    }

    @PostMapping(path = "/projects/add")
    public ApiResponse<Project> createProject(@RequestBody ProjectDto project) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Project saved successfully.",projectService.save(project));
    }

    @PutMapping(path = "/projects/{id}")
    public ApiResponse<UserDto> update(@RequestBody ProjectDto projectDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Project updated successfully.", projectService.update(projectDto));
    }

    @DeleteMapping("/projects/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        projectService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Project deleted successfully.", null);
    }
}
