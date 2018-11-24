package com.costsestimationbackend.costsestimationbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    //Read all
    @GetMapping(path = "/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    //Create
    @PostMapping("/users/add")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    //Read
    //odbiega od innych ale można porownac
    @GetMapping("/users/{id}")
    public @ResponseBody
    User getUserById(@PathVariable Integer id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    //Update
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Integer id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();

        user.setId(id);

        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }

    //delete
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
