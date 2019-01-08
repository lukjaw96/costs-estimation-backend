package com.costsestimationbackend.costsestimationbackend.service;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.EstimationDto;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.RequirementDto;

import java.util.List;

public interface RequirementService {

    Requirement save(RequirementDto requirement);

    List<RequirementDto> findAll();

    void delete(Integer id);

    RequirementDto findById(Integer id);

    RequirementDto update(RequirementDto requirementDto);

    List<EstimationDto> getRequirementEstimations(Integer idRequirement);

    List<?> getRequirementsParams();
}
