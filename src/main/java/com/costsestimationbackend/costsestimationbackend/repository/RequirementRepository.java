package com.costsestimationbackend.costsestimationbackend.repository;

import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequirementRepository extends CrudRepository<Requirement, Integer> {

    Iterable<Requirement> findByProjects_IdProject(Integer idProject);
    Requirement findByName(String name);

//    @Query("SELECT new com.costsestimationbackend.costsestimationbackend.model.Requirement.RequirementParamsDto()")
////    Collection<RequirementParamsDto> findAllActiveUsers();

    @Query(value = "select id_requirement AS idReq, avg(estimation) AS average, max(estimation) AS maximum, min(estimation) AS minimum from estimation_database.estimation group by id_requirement", nativeQuery = true)
    List<RequirementsParams> getRequirementsParams();
}

interface RequirementsParams {
    Double getIdReq();
    Double getAverage();
    Double getMaximum();
    Double getMinimum();
}