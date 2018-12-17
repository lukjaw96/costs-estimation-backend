package com.costsestimationbackend.costsestimationbackend.service;

import com.costsestimationbackend.costsestimationbackend.model.User.User;
import com.costsestimationbackend.costsestimationbackend.model.User.UserDto;
import com.costsestimationbackend.costsestimationbackend.model.User.UserPasswordUpdate;

import java.util.List;

public interface UserService {

    User save(UserDto user);

    List<UserDto> findAll();

    void delete(int id);

    User findOne(String username);

    UserDto findById(int id);

    UserDto update(UserDto userDto);

    UserDto updateSelf(UserDto userDto);

    void updatePassword(UserPasswordUpdate userPasswordUpdate);

    User findByUsername(String username);

}
