package com.costsestimationbackend.costsestimationbackend.controller;

import com.costsestimationbackend.costsestimationbackend.model.ApiResponse;
import com.costsestimationbackend.costsestimationbackend.model.User.User;
import com.costsestimationbackend.costsestimationbackend.model.User.UserDto;
import com.costsestimationbackend.costsestimationbackend.model.User.UserPasswordUpdate;
import com.costsestimationbackend.costsestimationbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public ApiResponse<List<User>> getAllUsers(){
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }

    @PostMapping(path = "/users/add")
    public ApiResponse<User> createUser(@RequestBody UserDto user) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
    }

    @PutMapping(path = "/users/{id}")
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", userService.update(userDto));
    }

    @PutMapping(path = "/users/{id}/password-update")
    public ApiResponse<Void> updatePassword(@RequestBody UserPasswordUpdate userPasswordUpdate) {
        userService.updatePassword(userPasswordUpdate);
        return new ApiResponse<>(HttpStatus.OK.value(), "User password updated successfully.", null);
    }

    @PutMapping(path = "/users/{id}/update-self")
    public ApiResponse<Void> updateSelf(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", userService.updateSelf(userDto));
    }

    @DeleteMapping("/users/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }
}
