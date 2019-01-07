package com.costsestimationbackend.costsestimationbackend.service;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Estimation.EstimationDto;

import java.util.List;

public interface EstimationService {
    Estimation save(EstimationDto estimationDto);
    EstimationDto findById(Integer id);
    List<EstimationDto> findAll();
    void delete(Integer id);
    EstimationDto update(EstimationDto estimationDto);
    List<?> getEstimationsRanges();
}
