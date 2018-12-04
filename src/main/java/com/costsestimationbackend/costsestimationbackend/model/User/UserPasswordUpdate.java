package com.costsestimationbackend.costsestimationbackend.model.User;

import com.costsestimationbackend.costsestimationbackend.model.User.UserDto;

public class UserPasswordUpdate extends UserDto {

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    private String oldPassword;

}
