package com.costsestimationbackend.costsestimationbackend.repository;

import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import org.springframework.data.repository.CrudRepository;

public interface RequirementRepository extends CrudRepository<Requirement, Integer> {

//    @Query("select requirement from Requirement ")
//    List<Requirement> findRequirementsWithProjectId(@Param("idProject") Project idProject);

    Iterable<Requirement> findByProjects_IdProject(Integer idProject);

}
