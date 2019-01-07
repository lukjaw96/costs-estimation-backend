package com.costsestimationbackend.costsestimationbackend.service;

import com.costsestimationbackend.costsestimationbackend.model.Role.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();
}
