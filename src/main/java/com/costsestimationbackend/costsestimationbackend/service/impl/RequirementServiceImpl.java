package com.costsestimationbackend.costsestimationbackend.service.impl;

import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.RequirementDto;
import com.costsestimationbackend.costsestimationbackend.repository.ProjectRepository;
import com.costsestimationbackend.costsestimationbackend.repository.RequirementRepository;
import com.costsestimationbackend.costsestimationbackend.service.RequirementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "requirementService")
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;

    @Override
    public Requirement findById(int id) {
        Optional<Requirement> optionalRequirement = requirementRepository.findById(id);
        return optionalRequirement.isPresent() ? optionalRequirement.get() : null;
    }

    public List<Requirement> findAll() {
        List<Requirement> list = new ArrayList<>();
        requirementRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Requirement save(RequirementDto requirement) {
        Requirement newRequirement = new Requirement();

        newRequirement.setName(requirement.getName());
        newRequirement.setDescription(requirement.getDescription());
        newRequirement.setAuthor(requirement.getAuthor());
        newRequirement.setCreationDate(requirement.getCreationDate());
        newRequirement.setFinalCost(requirement.getFinalCost());
        return requirementRepository.save(newRequirement);
    }


    @Override
    public RequirementDto update(RequirementDto requirementDto) {
        Requirement requirement = findById(requirementDto.getIdRequirement());
        if (requirement != null) {
            BeanUtils.copyProperties(requirementDto, requirement, "idRequirement");
            requirementRepository.save(requirement);
        }
        return requirementDto;
    }

    @Override
    public void delete(int id) {
        requirementRepository.deleteById(id);
    }

//    @Override
//    public List<Requirement> findByIdProject() {
//        List<Requirement> list = new ArrayList<>();
//        requirementRepository.findByProjects_IdProject("1").iterator().forEachRemaining(list::add);
//        System.out.println(list);
//        //list = requirementRepository.findByProjects_IdProject("1");
//        return list;
//    }
}