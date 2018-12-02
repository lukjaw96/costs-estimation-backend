package com.costsestimationbackend.costsestimationbackend.resource;

import com.costsestimationbackend.costsestimationbackend.config.jwt.JwtTokenUtil;
import com.costsestimationbackend.costsestimationbackend.model.ApiResponse;
import com.costsestimationbackend.costsestimationbackend.model.AuthToken;
import com.costsestimationbackend.costsestimationbackend.model.LoginUser;
import com.costsestimationbackend.costsestimationbackend.model.User;
import com.costsestimationbackend.costsestimationbackend.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AppUserDetailsService userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findOne(loginUser.getUsername());
        //dodanie roli
        final String role = userService.findOne(loginUser.getUsername()).getRole();
        System.out.println("role " + role);
        final String token = jwtTokenUtil.generateToken(user, role);
        return new ApiResponse<>(200, "success",new AuthToken(token, user.getUsername()));
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/users/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }
}
