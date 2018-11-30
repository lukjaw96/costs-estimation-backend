package com.costsestimationbackend.costsestimationbackend;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("Could not find user ");
    }
}
