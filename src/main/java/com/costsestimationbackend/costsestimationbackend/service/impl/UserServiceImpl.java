package com.costsestimationbackend.costsestimationbackend.service.impl;

import com.costsestimationbackend.costsestimationbackend.model.User.User;
import com.costsestimationbackend.costsestimationbackend.model.User.UserDto;
import com.costsestimationbackend.costsestimationbackend.model.User.UserPasswordUpdate;
import com.costsestimationbackend.costsestimationbackend.repository.UserRepository;
import com.costsestimationbackend.costsestimationbackend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        //adding role
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user.getRole()));
    }

    private List<SimpleGrantedAuthority> getAuthority(String role) {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public List<UserDto> findAll() {
        List<User> usersList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(usersList::add);
        List<UserDto> usersDtoList = new ArrayList<>();

        for (User user : usersList) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto, "password", "estimations");
            usersDtoList.add(userDto);
        }

        return usersDtoList;
    }

    @Override
    public UserDto findById(Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Integer idUser = userRepository.findByUsername(username).getIdUser();

        if (id == idUser) {

            Optional<User> optionalUser = userRepository.findById(id);
            User user = optionalUser.isPresent() ? optionalUser.get() : null;

            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto, "password", "estimations");

            return userDto;
        } else {
            return null;
        }
    }



    public void updatePassword(UserPasswordUpdate userPasswordUpdate) {
        Optional<User> optionalUser = userRepository.findById(userPasswordUpdate.getIdUser());
        User user = optionalUser.isPresent() ? optionalUser.get() : null;
        if(userPasswordUpdate.getPassword() != null) {
            Pattern patternPassword = Pattern.compile("\\s");
            Matcher matcherPassword = patternPassword.matcher(userPasswordUpdate.getPassword());
            boolean foundSpacesInPassword = matcherPassword.find();

            if (
                    !(user.getPassword() == null ||
                            user.getPassword() == "" ||
                            foundSpacesInPassword ||
                            user.getPassword().length() < 8)
            ) {
                userPasswordUpdate.setPassword(bcryptEncoder.encode(userPasswordUpdate.getPassword()));

                if (user != null) {
                    BeanUtils.copyProperties(userPasswordUpdate, user, "idUser", "oldPassword");
                    userRepository.save(user);
                }
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Integer idUser = findByUsername(username).getIdUser();

        if (!(id == idUser)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public UserDto updateSelf(UserDto userDto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if (userDto.getUsername().equals(username)) {
            User user = findByUsername(userDto.getUsername());
            if (user != null) {
                BeanUtils.copyProperties(userDto, user, "idUser", "username", "role", "password");
                userRepository.save(user);
            }
            return userDto;
        } else {
            return null;
        }
    }

    @Override
    public UserDto update(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userDto.getIdUser());
        User user = optionalUser.isPresent() ? optionalUser.get() : null;

        if (user != null) {
            BeanUtils.copyProperties(userDto, user, "password", "idUser", "username");
            userRepository.save(user);
        }
        return userDto;
    }

    @Override
    public User save(UserDto user) {
        if ((user.getUsername() != null) && (user.getPassword() != null)) {
            Pattern patternUsername = Pattern.compile("\\s");
            Matcher matcherUsername = patternUsername.matcher(user.getUsername());
            boolean foundSpacesInUser = matcherUsername.find();

            Pattern patternPassword = Pattern.compile("\\s");
            Matcher matcherPassword = patternPassword.matcher(user.getPassword());
            boolean foundSpacesInPassword = matcherPassword.find();

            boolean foundExistingUserInRepository = false;

            if (userRepository.findByUsername(user.getUsername()) != null) {
                foundExistingUserInRepository = true;
            }

            if (
                    !(user.getPassword() == null ||
                            user.getPassword() == "" ||
                            foundSpacesInPassword ||
                            user.getPassword().length() < 8 ||
                            user.getUsername() == null ||
                            user.getUsername() == "" ||
                            foundSpacesInUser ||

                            foundExistingUserInRepository)

            ) {
                User newUser = new User();
                newUser.setFirstName(user.getFirstName());
                newUser.setLastName(user.getLastName());
                newUser.setUsername(user.getUsername());
                newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
                newUser.setRole(user.getRole());
                return userRepository.save(newUser);
            }
        }
        return null;
    }
}
