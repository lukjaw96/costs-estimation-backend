package com.costsestimationbackend.costsestimationbackend;

class UserNotFoundException extends RuntimeException{

    UserNotFoundException() {
        super("Could not find user ");
    }
}
