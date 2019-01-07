package com.costsestimationbackend.costsestimationbackend.controller;

import com.costsestimationbackend.costsestimationbackend.model.ApiResponse;
import com.costsestimationbackend.costsestimationbackend.model.User.User;
import com.costsestimationbackend.costsestimationbackend.model.User.UserDto;
import com.costsestimationbackend.costsestimationbackend.model.User.UserPasswordUpdate;
import com.costsestimationbackend.costsestimationbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ApiResponse<List<User>> getAllUsers(){
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", userService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/add")
    public ApiResponse<User> createUser(@RequestBody UserDto user) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}")
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", userService.update(userDto));
    }

    @PutMapping(path = "/{id}/password-update")
    public ApiResponse<Void> updatePassword(@RequestBody UserPasswordUpdate userPasswordUpdate) {
        userService.updatePassword(userPasswordUpdate);
        return new ApiResponse<>(HttpStatus.OK.value(), "User password updated successfully.", null);
    }

    @PutMapping(path = "/{id}/update-self")
    public ApiResponse<Void> updateSelf(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", userService.updateSelf(userDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }
}
