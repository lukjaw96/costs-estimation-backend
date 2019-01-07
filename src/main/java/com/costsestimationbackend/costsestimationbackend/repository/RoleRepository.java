package com.costsestimationbackend.costsestimationbackend.repository;

import com.costsestimationbackend.costsestimationbackend.model.Role.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
