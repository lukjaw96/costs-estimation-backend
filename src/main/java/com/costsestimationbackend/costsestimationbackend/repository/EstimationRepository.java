package com.costsestimationbackend.costsestimationbackend.repository;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstimationRepository extends CrudRepository<Estimation, Integer> {
    //Iterable<Estimation> findByRequirements_IdRequirement(Integer idRequirement);

    @Query(value = "SELECT id_user AS idUser, sum(estimation > 0 and estimation <= 3) as 'range1_3', sum(estimation >3 and estimation <= 6) AS 'range4_6', sum(estimation >6 and estimation <= 9) AS 'range7_9', sum(estimation > 9 and estimation <= 12) as 'range10_12' from estimation group by id_user", nativeQuery = true)
    List<EstimationsRanges> getEstimationsRanges();
}

interface EstimationsRanges {
    Double getidUser();
    Double getrange1_3();
    Double getrange4_6();
    Double getrange7_9();
    Double getrange10_12();
}
