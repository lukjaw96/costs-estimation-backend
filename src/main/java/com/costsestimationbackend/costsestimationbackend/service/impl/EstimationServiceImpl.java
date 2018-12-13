package com.costsestimationbackend.costsestimationbackend.service.impl;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Estimation.EstimationDto;
import com.costsestimationbackend.costsestimationbackend.model.Project.Project;
import com.costsestimationbackend.costsestimationbackend.model.Project.ProjectDto;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.RequirementDto;
import com.costsestimationbackend.costsestimationbackend.model.User.User;
import com.costsestimationbackend.costsestimationbackend.repository.EstimationRepository;
import com.costsestimationbackend.costsestimationbackend.repository.RequirementRepository;
import com.costsestimationbackend.costsestimationbackend.repository.UserRepository;
import com.costsestimationbackend.costsestimationbackend.service.EstimationService;
import com.costsestimationbackend.costsestimationbackend.service.ProjectService;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "estimationService")
public class EstimationServiceImpl implements EstimationService {

    @Autowired
    EstimationRepository estimationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RequirementRepository requirementRepository;

    @Override
    public EstimationDto findById(int id) {
        Optional<Estimation> optionalEstimation = estimationRepository.findById(id);
        Estimation newEstimation = optionalEstimation.isPresent() ? optionalEstimation.get() : null;
        return new EstimationDto(newEstimation.getIdEstimation(), newEstimation.getUser().getIdUser(), newEstimation.getRequirement().getIdRequirement(), newEstimation.getEstimation());
        //return optionalEstimation.isPresent() ? optionalEstimation.get() : null;
    }

    public List<EstimationDto> findAll() {
        List<Estimation> list = new ArrayList<>();
        estimationRepository.findAll().iterator().forEachRemaining(list::add);

        //Hibernate.initialize(estimation.getRequirements());

        List<EstimationDto> listEstimationsDto = new ArrayList<>();


        for (Estimation pro : list) {
            EstimationDto newEstimation = new EstimationDto(
                    pro.getIdEstimation(),
                    pro.getUser().getIdUser(),
                    pro.getRequirement().getIdRequirement(),
                    pro.getEstimation()
            );
            listEstimationsDto.add(newEstimation);
        }


        return listEstimationsDto;
    }

    @Transactional()
    @Override
    public Estimation save(EstimationDto estimationDto) {
        Estimation estimation = new Estimation();

        //estimation.setIdEstimation(estimationDto.getIdEstimation());
        estimation.setEstimation(estimationDto.getEstimation());

        Optional<User> optionalUser = userRepository.findById(estimationDto.getIdUser());
        estimation.setUser(optionalUser.isPresent() ? optionalUser.get() : null);

        Hibernate.initialize((optionalUser.isPresent() ? optionalUser.get() : null).getEstimations());

        Optional<Requirement> optionalRequirement = requirementRepository.findById(estimationDto.getIdRequirement());
        estimation.setRequirement(optionalRequirement.isPresent() ? optionalRequirement.get() : null);

        Hibernate.initialize((optionalRequirement.isPresent() ? optionalRequirement.get() : null).getProjects());
        Hibernate.initialize((optionalRequirement.isPresent() ? optionalRequirement.get() : null).getEstimations());
        return estimationRepository.save(estimation);
    }

    @Override
    public EstimationDto update(EstimationDto estimationDto) {
        Optional<Estimation> optionalEstimation = estimationRepository.findById(estimationDto.getIdEstimation());
        Estimation estimation = optionalEstimation.isPresent() ? optionalEstimation.get() : null;

        if (estimation != null) {
            BeanUtils.copyProperties(estimationDto, estimation, "idEstimation");
            estimationRepository.save(estimation);
        }
        return estimationDto;
    }

    @Override
    public void delete(int id) {
        estimationRepository.deleteById(id);
    }

}
