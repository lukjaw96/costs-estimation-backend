package com.costsestimationbackend.costsestimationbackend.repository;

import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import org.aspectj.weaver.ast.Test;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequirementRepository extends CrudRepository<Requirement, Integer> {

//    @Query("select requirement from Requirement ")
//    List<Requirement> findRequirementsWithProjectId(@Param("idProject") Project idProject);

//    Iterable<Requirement> findByProjects_IdProject(String idProject);

}
