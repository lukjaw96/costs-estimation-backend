package com.costsestimationbackend.costsestimationbackend.controller;

import com.costsestimationbackend.costsestimationbackend.model.ApiResponse;
import com.costsestimationbackend.costsestimationbackend.model.Estimation.Estimation;
import com.costsestimationbackend.costsestimationbackend.model.Estimation.EstimationDto;
import com.costsestimationbackend.costsestimationbackend.service.EstimationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/estimations")
public class EstimationController {

    @Autowired
    EstimationService estimationService;

    @GetMapping()
    public ApiResponse<List<Estimation>> getAllEstimations() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Estimations list fetched successfully.", estimationService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<EstimationDto> getOne(@PathVariable Integer id) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Estimation fetched successfully.", estimationService.findById(id));
    }

    @PostMapping(path = "/add")
    public ApiResponse<Estimation> createEstimation(@RequestBody EstimationDto estimationDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Estimation saved successfully.", estimationService.save(estimationDto));
    }

    @PutMapping(path = "/{id}")
    public ApiResponse<EstimationDto> update(@RequestBody EstimationDto estimationDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Estimation updated successfully.", estimationService.update(estimationDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        estimationService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Estimation deleted successfully.", null);
    }

    @GetMapping("/ranges")
    public ApiResponse<?> getEstimationsRanges() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Estimation fetched successfully.", estimationService.getEstimationsRanges());
    }
}
