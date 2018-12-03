package com.costsestimationbackend.costsestimationbackend.service;

//import com.costsestimationbackend.costsestimationbackend.model.CustomUserDetails;
import com.costsestimationbackend.costsestimationbackend.model.User;
import com.costsestimationbackend.costsestimationbackend.model.UserDto;
import com.costsestimationbackend.costsestimationbackend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(int id);

    User findOne(String username);

    User findById(int id);

    UserDto update(UserDto userDto);

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bcryptEncoder;
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if(user == null){
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        //adding role
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user.getRole()));
//    }
//
//    private List<SimpleGrantedAuthority> getAuthority(String role) {
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role));
//    }
//
//    public List<User> findAll() {
//        List<User> list = new ArrayList<>();
//        userRepository.findAll().iterator().forEachRemaining(list::add);
//        return list;
//    }
//
//    public void delete(int id) {
//        userRepository.deleteById(id);
//    }
//
//    public User findOne(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    public User findById(int id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        return optionalUser.isPresent() ? optionalUser.get() : null;
//    }
//
//    public UserDto update(UserDto userDto) {
//        User user = findById(userDto.getId());
//        if(user != null) {
//            BeanUtils.copyProperties(userDto, user, "password");
//            userRepository.save(user);
//        }
//        return userDto;
//    }
//
//    public User save(UserDto user) {
//        User newUser = new User();
//        newUser.setUsername("blah");
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
//        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//        return userRepository.save(newUser);
//    }
}
