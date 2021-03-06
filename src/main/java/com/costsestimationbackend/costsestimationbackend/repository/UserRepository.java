package com.costsestimationbackend.costsestimationbackend.repository;

import com.costsestimationbackend.costsestimationbackend.model.User.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

}
