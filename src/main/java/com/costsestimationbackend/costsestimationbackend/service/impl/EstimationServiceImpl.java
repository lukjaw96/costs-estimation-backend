package com.costsestimationbackend.costsestimationbackend.service.impl;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Estimation.EstimationDto;
import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;
import com.costsestimationbackend.costsestimationbackend.repository.EstimationRepository;
import com.costsestimationbackend.costsestimationbackend.service.EstimationService;
import com.costsestimationbackend.costsestimationbackend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "estimationService")
public class EstimationServiceImpl implements EstimationService {

    @Autowired
    EstimationRepository estimationRepository;
    @Override
    public Estimation save(Estimation estimation) {

        Estimation newEstimation = new Estimation();

        newEstimation.setIdEstimation(estimation.getIdEstimation());
        newEstimation.setEstimation(estimation.getEstimation());
        newEstimation.setRequirement(estimation.getRequirement());
        newEstimation.setUser(estimation.getUser());


        return estimationRepository.save(newEstimation);

    }
}
