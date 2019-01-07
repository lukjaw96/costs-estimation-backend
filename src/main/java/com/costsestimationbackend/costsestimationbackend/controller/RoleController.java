package com.costsestimationbackend.costsestimationbackend.controller;

import com.costsestimationbackend.costsestimationbackend.model.ApiResponse;
import com.costsestimationbackend.costsestimationbackend.model.Role.Role;
import com.costsestimationbackend.costsestimationbackend.model.Role.RoleDto;
import com.costsestimationbackend.costsestimationbackend.model.User.User;
import com.costsestimationbackend.costsestimationbackend.model.User.UserDto;
import com.costsestimationbackend.costsestimationbackend.model.User.UserPasswordUpdate;
import com.costsestimationbackend.costsestimationbackend.service.RoleService;
import com.costsestimationbackend.costsestimationbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ApiResponse<List<Role>> getAllRoles(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Role list fetched successfully.", roleService.findAll());
    }
}

