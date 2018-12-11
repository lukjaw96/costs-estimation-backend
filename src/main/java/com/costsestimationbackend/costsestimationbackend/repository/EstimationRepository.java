package com.costsestimationbackend.costsestimationbackend.repository;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import org.springframework.data.repository.CrudRepository;

public interface EstimationRepository extends CrudRepository<Estimation, Integer> {
}
