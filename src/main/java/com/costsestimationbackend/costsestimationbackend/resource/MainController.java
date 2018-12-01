package com.costsestimationbackend.costsestimationbackend.resource;

import com.costsestimationbackend.costsestimationbackend.UserNotFoundException;
import com.costsestimationbackend.costsestimationbackend.config.JwtTokenUtil;
import com.costsestimationbackend.costsestimationbackend.model.ApiResponse;
import com.costsestimationbackend.costsestimationbackend.model.AuthToken;
import com.costsestimationbackend.costsestimationbackend.model.LoginUser;
import com.costsestimationbackend.costsestimationbackend.model.User;
import com.costsestimationbackend.costsestimationbackend.repository.UserRepository;
import com.costsestimationbackend.costsestimationbackend.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.Optional;

@CrossOrigin
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/users") // This means URL's start with /demo (after Application path)
public class MainController {
//    @Autowired // This means to get the bean called userRepository
//    // Which is auto-generated by Spring, we will use it to handle the data
//    private UserRepository userRepository;
//
//    //Read all
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    @GetMapping(path = "/users")
//    public @ResponseBody
//    Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return userRepository.findAll();
//    }
//
////    @RequestMapping("/login")
////    public @ResponseBody boolean login(@RequestBody User user) {
////
//////        return
//////                user.getLogin().equals("user") && user.getPassword().equals("user") ||
//////                        user.getLogin().equals("admin") && user.getPassword().equals("admin");
////        User foundUser = userRepository.findByLogin(user.getLogin())
////                .orElseThrow(() -> new UserNotFoundException());
////        if(foundUser.getPassword().equals(user.getPassword())){
////            return true;
////        }
////        return false;
////    }
//
//    //Create
//    @PostMapping("/users/add")
//    public ResponseEntity<Object> createUser(@RequestBody User user) {
//        User savedUser = userRepository.save(user);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(savedUser.getId()).toUri();
//
//        return ResponseEntity.created(location).build();
//
//    }
//
//    //Read
//    //odbiega od innych ale można porownac
//    @GetMapping("/users/{id}")
//    public @ResponseBody
//    User getUserById(@PathVariable Integer id) {
//
//        return userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException());
//    }
//
//    //Update
//    @PutMapping("/users/{id}")
//    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Integer id) {
//
//        Optional<User> userOptional = userRepository.findById(id);
//
//        if (!userOptional.isPresent())
//            return ResponseEntity.notFound().build();
//
//        user.setId(id);
//
//        userRepository.save(user);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    //delete
//    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable Integer id) {
//        userRepository.deleteById(id);
//    }



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

        //get role of user and pass it to the token generator
        final String role = userService.findOne(loginUser.getUsername()).getRole();
        System.out.println("role " + role);
        final String token = jwtTokenUtil.generateToken(user, role);
        return new ApiResponse<>(200, "success",new AuthToken(token, user.getUsername()));
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }
}
