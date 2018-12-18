package com.costsestimationbackend.costsestimationbackend.controller;

import com.costsestimationbackend.costsestimationbackend.model.ApiResponse;
import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.RequirementDto;
import com.costsestimationbackend.costsestimationbackend.model.User.UserDto;
import com.costsestimationbackend.costsestimationbackend.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RequirementController {
    @Autowired
    private RequirementService requirementService;

    @GetMapping(path = "/requirements")
    public ApiResponse<List<Requirement>> getAllRequirements(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Requirements list fetched successfully.", requirementService.findAll());
//        return new ApiResponse<>(HttpStatus.OK.value(), "Requirements list fetched successfully.", requirementService.findByIdProject());
    }

    @GetMapping("/requirements/{id}")
    public ApiResponse<Requirement> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Requirement fetched successfully.",requirementService.findById(id));
    }

    @PreAuthorize("hasRole('ANALYST')")
    @PostMapping(path = "/requirements/add")
    public ApiResponse<Project> createProject(@RequestBody RequirementDto project) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Requirement saved successfully.",requirementService.save(project));
    }

    @PreAuthorize("hasRole('ANALYST')")
    @PutMapping(path = "/requirements/{id}")
    public ApiResponse<UserDto> update(@RequestBody RequirementDto requirementDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Requirement updated successfully.", requirementService.update(requirementDto));
    }

    @PreAuthorize("hasRole('ANALYST')")
    @DeleteMapping("/requirements/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        requirementService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Requirement deleted successfully.", null);
    }

    @GetMapping(path = "/requirements/{idRequirement}/estimations")
    public ApiResponse<List<Estimation>> getRequirementEstimations(@PathVariable int idRequirement) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Requirement estimations fetched successfully", requirementService.getRequirementEstimations(idRequirement));
    }

    @GetMapping("/requirements/params")
    public ApiResponse<?> getRequirementsParams(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Requirement fetched successfully.",requirementService.getRequirementsParams());
    }
}
