package com.costsestimationbackend.costsestimationbackend;

class UserNotFoundException extends RuntimeException{

    UserNotFoundException(Integer id) {
        super("Could not find user " + id);
    }
}
