package com.costsestimationbackend.costsestimationbackend.service.impl;

import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Estimation.EstimationDto;
import com.costsestimationbackend.costsestimationbackend.model.Requirement.Requirement;
import com.costsestimationbackend.costsestimationbackend.model.User.User;
import com.costsestimationbackend.costsestimationbackend.repository.EstimationRepository;
import com.costsestimationbackend.costsestimationbackend.repository.RequirementRepository;
import com.costsestimationbackend.costsestimationbackend.repository.UserRepository;
import com.costsestimationbackend.costsestimationbackend.service.EstimationService;
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
    public EstimationDto findById(Integer id) {
        Optional<Estimation> optionalEstimation = estimationRepository.findById(id);
        Estimation newEstimation = optionalEstimation.isPresent() ? optionalEstimation.get() : null;
        return new EstimationDto(newEstimation.getIdEstimation(), newEstimation.getUser().getIdUser(), newEstimation.getRequirement().getIdRequirement(), newEstimation.getEstimation());
    }

    public List<EstimationDto> findAll() {
        List<Estimation> list = new ArrayList<>();
        estimationRepository.findAll().iterator().forEachRemaining(list::add);
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
        if(estimationDto.getEstimation() != null) {
            if(estimationRepository.checkIfEstimationAlreadyExist(estimationDto.getIdUser(), estimationDto.getIdRequirement()).isEmpty()) {
                Estimation estimation = new Estimation();
                estimation.setEstimation(estimationDto.getEstimation());
                Optional<User> optionalUser = userRepository.findById(estimationDto.getIdUser());
                if(optionalUser.isPresent()) {
                    optionalUser.get().setEstimations(null);
                }
                estimation.setUser(optionalUser.isPresent() ? optionalUser.get() : null);
                Optional<Requirement> optionalRequirement = requirementRepository.findById(estimationDto.getIdRequirement());
                if(optionalRequirement.isPresent()) {
                    optionalRequirement.get().setEstimations(null);
                }
                if(optionalRequirement.isPresent()) {
                    optionalRequirement.get().setProjects(null);
                }
                estimation.setRequirement(optionalRequirement.isPresent() ? optionalRequirement.get() : null);
                return estimationRepository.save(estimation);
            }
        }
        return null;
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
    public void delete(Integer id) {
        estimationRepository.deleteById(id);
    }

    @Override
    public List<?> getEstimationsRanges() {
        return estimationRepository.getEstimationsRanges();
    }

}
