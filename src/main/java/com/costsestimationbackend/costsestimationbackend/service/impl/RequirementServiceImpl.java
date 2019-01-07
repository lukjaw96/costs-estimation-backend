package com.costsestimationbackend.costsestimationbackend.service.impl;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Estimation.EstimationDto;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.RequirementDto;
import com.costsestimationbackend.costsestimationbackend.repository.RequirementRepository;
import com.costsestimationbackend.costsestimationbackend.repository.UserRepository;
import com.costsestimationbackend.costsestimationbackend.service.RequirementService;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service(value = "requirementService")
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RequirementDto findById(Integer id) {

        Optional<Requirement> optionalRequirement = requirementRepository.findById(id);
        Requirement newRequirement = optionalRequirement.isPresent() ? optionalRequirement.get() : null;
        return new RequirementDto(
                newRequirement.getIdRequirement(),
                newRequirement.getName(),
                newRequirement.getDescription(),
                newRequirement.getAuthor(),
                newRequirement.getCreationDate(),
                newRequirement.getFinalCost()
        );
    }

    public List<RequirementDto> findAll() {
        List<Requirement> list = new ArrayList<>();
        requirementRepository.findAll().iterator().forEachRemaining(list::add);
        List<RequirementDto> listRequirementsDto = new ArrayList<>();
        for (Requirement req : list) {
            RequirementDto newRequirement = new RequirementDto(
                    req.getIdRequirement(),
                    req.getName(),
                    req.getDescription(),
                    req.getAuthor(),
                    req.getCreationDate(),
                    req.getFinalCost()
            );
            listRequirementsDto.add(newRequirement);
        }
        return listRequirementsDto;
    }

    @Transactional
    @Override
    public Requirement save(RequirementDto requirement) {
        if(requirement.getName() != null){
        Pattern patternName = Pattern.compile("\\s");
        Matcher matcherName = patternName.matcher(requirement.getName());
        boolean foundSpacesInName = matcherName.find();
        boolean foundExistingRequirementInRepository = false;
        if (requirementRepository.findByName(requirement.getName()) != null) {
            foundExistingRequirementInRepository = true;
        }
        if (!(requirement.getName() == "" || foundSpacesInName || foundExistingRequirementInRepository)) {
            Requirement newRequirement = new Requirement();
            newRequirement.setIdRequirement(requirement.getIdRequirement());
            newRequirement.setName(requirement.getName());
            newRequirement.setDescription(requirement.getDescription());
            newRequirement.setAuthor(requirement.getAuthor());
            newRequirement.setCreationDate(requirement.getCreationDate());
            newRequirement.setFinalCost(requirement.getFinalCost());
            return requirementRepository.save(newRequirement);
        }
    }
        return null;
    }

    @Override
    public RequirementDto update(RequirementDto requirementDto) {
        if(requirementDto.getName() != null){
            Pattern patternName = Pattern.compile("\\s");
            Matcher matcherName = patternName.matcher(requirementDto.getName());
            boolean foundSpacesInName = matcherName.find();

            boolean foundExistingRequirementInRepository = false;

            if (requirementRepository.findByName(requirementDto.getName()) != null) {
                foundExistingRequirementInRepository = true;
            }

            if (!(requirementDto.getName() == null || requirementDto.getName() == "" || foundSpacesInName || foundExistingRequirementInRepository)) {
                Optional<Requirement> optionalRequirement = requirementRepository.findById(requirementDto.getIdRequirement());
                Requirement requirement = optionalRequirement.isPresent() ? optionalRequirement.get() : null;
                if (requirement != null) {
                    BeanUtils.copyProperties(requirementDto, requirement, "idRequirement");
                    requirementRepository.save(requirement);
                }
                return requirementDto;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        requirementRepository.deleteById(id);
    }

    @Override
    public List<Requirement> findByIdProject() {
        List<Requirement> list = new ArrayList<>();
        requirementRepository.findByProjects_IdProject(1).iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public List<EstimationDto> getRequirementEstimations(Integer idRequirement) {
        Optional<Requirement> optionalRequirement = requirementRepository.findById(idRequirement);
        Requirement requirement = optionalRequirement.isPresent() ? optionalRequirement.get() : null;
        Hibernate.initialize(requirement.getEstimations());
        List<EstimationDto> listEstimations = new ArrayList<>();
        for (Estimation est : requirement.getEstimations()) {
            EstimationDto newEstimation = new EstimationDto(
                    est.getIdEstimation(),
                    est.getUser().getIdUser(),
                    est.getRequirement().getIdRequirement(),
                    est.getEstimation()
            );
            listEstimations.add(newEstimation);
        }
        return listEstimations;
    }

    @Override
    public List<?> getRequirementsParams() {
        return requirementRepository.getRequirementsParams();
    }
}
