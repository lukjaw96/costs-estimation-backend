package com.costsestimationbackend.costsestimationbackend.service;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Estimation.EstimationDto;
import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;

import java.util.List;

public interface EstimationService {
    Estimation save(EstimationDto estimationDto);
    EstimationDto findById(int id);
    List<EstimationDto> findAll();
    void delete(int id);
    EstimationDto update(EstimationDto estimationDto);
}
