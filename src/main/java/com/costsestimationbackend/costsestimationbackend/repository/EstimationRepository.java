package com.costsestimationbackend.costsestimationbackend.repository;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import org.springframework.data.repository.CrudRepository;

public interface EstimationRepository extends CrudRepository<Estimation, Integer> {
    //Iterable<Estimation> findByRequirements_IdRequirement(Integer idRequirement);
}
