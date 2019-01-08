package com.costsestimationbackend.costsestimationbackend.repository;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstimationRepository extends CrudRepository<Estimation, Integer> {

    @Query(value = "SELECT id_user AS idUser, sum(estimation > 0 and estimation <= 3) as 'range1_3', sum(estimation >3 and estimation <= 6) AS 'range4_6', sum(estimation >6 and estimation <= 9) AS 'range7_9', sum(estimation > 9 and estimation <= 12) as 'range10_12' from estimation group by id_user", nativeQuery = true)
    List<EstimationsRanges> getEstimationsRanges();

    @Query(value = "SELECT id_estimation AS idEstimation, estimation AS estimation, id_requirement AS idRequirement, id_user AS idUser FROM estimation WHERE estimation.id_user = ?1 AND estimation.id_requirement = ?2", nativeQuery = true)
    List<EstimationDto2> checkIfEstimationAlreadyExist(Integer idUser, Integer idRequirement);

}

interface EstimationsRanges {
    Double getidUser();
    Double getrange1_3();
    Double getrange4_6();
    Double getrange7_9();
    Double getrange10_12();
}

interface EstimationDto2 {
    Double getidEstimation();
    Double getestimation();
    Double getidRequirement();
    Double getidUser();
}
