package com.costsestimationbackend.costsestimationbackend.service;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.EstimationDto;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.RequirementDto;

import java.util.List;

public interface RequirementService {

    Requirement save(RequirementDto requirement);

    List<RequirementDto> findAll();

    void delete(int id);

    RequirementDto findById(int id);

    RequirementDto update(RequirementDto requirementDto);

    List<Requirement> findByIdProject();

    List<EstimationDto> getRequirementEstimations(int idRequirement);

}
