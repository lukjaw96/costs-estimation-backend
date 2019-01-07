package com.costsestimationbackend.costsestimationbackend.service.impl;

import com.costsestimationbackend.costsestimationbackend.model.Role.Role;
import com.costsestimationbackend.costsestimationbackend.model.Role.RoleDto;
import com.costsestimationbackend.costsestimationbackend.repository.RoleRepository;
import com.costsestimationbackend.costsestimationbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDto> findAll() {
        List<Role> list = new ArrayList<>();
        roleRepository.findAll().iterator().forEachRemaining(list::add);
        List<RoleDto> listRolesDto = new ArrayList<>();

        for (Role role : list) {
            RoleDto newRole = new RoleDto(
                    role.getIdRole(),
                    role.getCode(),
                    role.getName()
            );
            listRolesDto.add(newRole);
        }
        return listRolesDto;
    }
}
